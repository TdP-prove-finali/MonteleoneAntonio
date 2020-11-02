package it.polito.tdp.trasportoRifiuti.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.trasportoRifiuti.db.trasportoRifiutiDAO;

public class Model {
	
	private trasportoRifiutiDAO dao;
	private List<String> ragioneSocialeProduttore;
	private List<String> ragioneSocialeSmaltitore;
	private List<String> descrizioneEuropea1;
	private List<String> ragioneSocialeTrasportatore;
	private List<MezzoDiTrasporto> mezzi;
	
	public Model() {
		this.dao = new trasportoRifiutiDAO();
	}
	
	public List<String> getProduttori(){
		this.ragioneSocialeProduttore = new ArrayList<>(this.dao.listAllProduttori());
		return this.ragioneSocialeProduttore;
	}
	
	public List<String> getSmaltitori(){
		this.ragioneSocialeSmaltitore = new ArrayList<>(this.dao.listAllSmaltitori());
		return this.ragioneSocialeSmaltitore;
	}

	public String getRicercaProduttori(String ragioneSoc, int meseIniziale, int meseFinale) {
		return this.dao.testoRicercaProduttori(ragioneSoc, meseIniziale, meseFinale);
	}
	
	public String getRicercaSmaltitori(String ragioneSoc, int meseIniziale, int meseFinale) {
		return this.dao.testoRicercaSmaltitori(ragioneSoc, meseIniziale, meseFinale);
	}
	
	public List<String> getDescrizioni(){
		this.descrizioneEuropea1 = new ArrayList<>(this.dao.listAllDescrizioni());
		return this.descrizioneEuropea1;
	}
	
	public List<String> getTrasportatori(String descrizione){
		this.ragioneSocialeTrasportatore = new ArrayList<>(this.dao.listAllTrasportatori(descrizione));
		return this.ragioneSocialeTrasportatore;
	}
	
	public void creaListaMezziDiTrasporto() {
		this.mezzi = new ArrayList<>();
	}
	
	public void aggiungiMezziDiTrasporto(String trasportatore,int quantita, int capienza) {
		
		int count = 0;
    	
    	for(MezzoDiTrasporto m: this.mezzi) {
    		if(m.getTrasportatore().equals(trasportatore)) {
    			count++;
    		}
    	}
    	
    	for(int i = 1; i<=quantita; i++) {
    		MezzoDiTrasporto mezzo = new MezzoDiTrasporto(count+1, trasportatore, capienza);
    		this.mezzi.add(mezzo);
    		count++;
    	}
	}

	public List<MezzoDiTrasporto> getMezzi() {
		return mezzi;
	}
	
	public void cancellaMezziDiTrasporto(String trasportatore) {
		
		List<MezzoDiTrasporto> eliminati = new ArrayList<>();

		for(MezzoDiTrasporto m: this.mezzi) {
			if(m.getTrasportatore().equals(trasportatore)) {
				eliminati.add(m);
			}
		}
		
		this.mezzi.removeAll(eliminati);
		
	}
	
}
