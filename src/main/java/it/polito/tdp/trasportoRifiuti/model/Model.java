package it.polito.tdp.trasportoRifiuti.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.trasportoRifiuti.db.trasportoRifiutiDAO;

public class Model {
	
	private trasportoRifiutiDAO dao;
	private List<String> ragioneSocialeProduttore;
	private List<String> ragioneSocialeSmaltitore;
	
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
}
