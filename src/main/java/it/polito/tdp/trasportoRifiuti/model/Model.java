package it.polito.tdp.trasportoRifiuti.model;

import it.polito.tdp.trasportoRifiuti.db.trasportoRifiutiDAO;

public class Model {
	//esempio
	private trasportoRifiutiDAO dao = new trasportoRifiutiDAO();
	
	public void verifica() {
		this.dao.listDescrizioni();
	}

}
