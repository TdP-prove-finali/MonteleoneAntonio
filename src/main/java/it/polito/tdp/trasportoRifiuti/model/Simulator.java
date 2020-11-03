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
			Event e = new Event (EventType.NUOVA_REGISTRAZIONE,r.getData(),r.getRagSocTrasp(),r.getZonaRaccolta(),r.getPeso());
			this.queue.add(e);
		}
		
		LocalDate giorno = LocalDate.of(2019, 1, 1);
		
		while(giorno.getYear()<2020) {
			Event ev = new Event(EventType.FINE_GIORNATA,giorno,null,null,0);
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
		
		return 0;
	}

	private void processEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	

}
