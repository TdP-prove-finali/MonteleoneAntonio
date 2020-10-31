package trasportoRifiuti.model;

import trasportoRifiuti.db.trasportoRifiutiDAO;

public class Model {
	
	private trasportoRifiutiDAO dao = new trasportoRifiutiDAO();
	
	public void verifica() {
		this.dao.listDescrizioni();
	}

}
