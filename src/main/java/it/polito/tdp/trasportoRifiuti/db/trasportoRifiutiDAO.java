package it.polito.tdp.trasportoRifiuti.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class trasportoRifiutiDAO {

	//esempio
	public void listDescrizioni(){
		String sql = "SELECT distinct Descrizione_Europea1 " + 
				"FROM registro_rifiuti";
		List<String> result = new ArrayList<String>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				
				//result.add(res.getString("Descrizione_Europea1"));
				System.out.println(res.getString("Descrizione_Europea1"));
			}
			conn.close();
			//return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			//return null;
		}
	}
	
}

