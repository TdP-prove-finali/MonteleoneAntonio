package it.polito.tdp.trasportoRifiuti.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import it.polito.tdp.trasportoRifiuti.model.Event.EventType;

public class Simulator {
	
	//PARAMETRI DI SIMULAZIONE
	
	private int probabilita;
	
	private int percentuale;
	
	private int maxSpostamenti;
	
	private List<MezzoDiTrasporto> mezzi;
	
	//CODA DEGLI EVENTI
	
	private PriorityQueue<Event> queue;
	
	//STATO DEL SISTEMA
	
	private String trasportatorePrecedente;
	private Map<MezzoDiTrasporto,Integer> mezziDisponibili;
	
	//VALORI DA CALCOLARE
	
	private Map<String,Integer> zone;
	
	private Map<MezzoDiTrasporto,Integer> inattivi;

	//METODI PER RESTITUIRE I RISULTATI
	
	public Map<String, Integer> getZone() {
		return zone;
	}

	public Map<MezzoDiTrasporto, Integer> getInattivi() {
		return inattivi;
	}
	
	//INIZIALIZZAZIONE

	public void simula(List<Registrazione> registrazioni, List<String> zoneDiRaccolta, List<MezzoDiTrasporto> mezziTrasp,
			int p, int perc, int max) {
		
		this.mezzi = new ArrayList<>(mezziTrasp);
		
		this.probabilita = p;
		this.percentuale = perc;
		this.maxSpostamenti = max;
		
		this.zone = new TreeMap<>();
		
		this.inattivi = new TreeMap<>();
		
		for(String z: zoneDiRaccolta) {
			this.zone.put(z, 0);
		}
		
		for(MezzoDiTrasporto m: this.mezzi) {
			this.inattivi.put(m,0);
		}
		
		this.queue = new PriorityQueue<>();
		
		for(Registrazione r: registrazioni) {
			int peso = modificaPeso(r.getPeso());
			Event e = new Event (EventType.NUOVA_REGISTRAZIONE,r.getData(),r.getRagSocTrasp(),r.getZonaRaccolta(),peso);
			this.queue.add(e);
		}
		
		this.mezziDisponibili = new TreeMap<>();
		
		for(MezzoDiTrasporto m: this.mezzi) {
			this.mezziDisponibili.put(m, 0);
		}
		
		LocalDate giorno = LocalDate.of(2019, 1, 1);
		
		this.trasportatorePrecedente="ZZZZZZ";
		
		while(giorno.getYear()<2020) {
			Event ev = new Event(EventType.FINE_GIORNATA,giorno,"ZZZZZZ",null,0);
			this.queue.add(ev);
			giorno = giorno.plusDays(1);
		}
		
		while(!this.queue.isEmpty()) {
			Event event = this.queue.poll();
			System.out.println(event.toString());
			processEvent(event);
		}
		
	}

	private int modificaPeso(int peso) {
		
		int nuovoPeso = peso;
		
		double n = Math.random()*100;
		
		if(n<this.probabilita) {
			
		double variazione = (this.percentuale*Math.random())/100;
		
		double segno = Math.random();
		
		if(segno<0.5) {
			nuovoPeso = (int) (nuovoPeso-nuovoPeso*variazione);
		}else {
			nuovoPeso = (int) (nuovoPeso+nuovoPeso*variazione);
		}
			
		}
		
		if(nuovoPeso<=0) {
			return peso;
		}
		
		return nuovoPeso;
	}

	private void processEvent(Event event) {
		switch(event.getType()) {
		
		case NUOVA_REGISTRAZIONE:
			
			String trasportatore = event.getRagSocTrasp();
			int rifiuti = event.getPeso();
			boolean flag = true;
			
			for(int i = 0; i<this.maxSpostamenti; i++) {
			for(MezzoDiTrasporto m: this.mezziDisponibili.keySet()) {
				if(m.getTrasportatore().equals(trasportatore)) {
				if(this.mezziDisponibili.get(m)==i && flag==true) {
					int capienza = m.getCapienza();
					if(capienza>=rifiuti) {
						rifiuti = 0;
						flag = false;
					}else {
						rifiuti = rifiuti-capienza;
					}
					this.mezziDisponibili.put(m, i+1);
				}
				}
			}
			}
			
			
			
			break;
			
		case FINE_GIORNATA:
			
			break;
		}
		
	}

	

}
