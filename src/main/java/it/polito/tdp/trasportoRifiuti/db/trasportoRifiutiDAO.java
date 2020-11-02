package it.polito.tdp.trasportoRifiuti.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class trasportoRifiutiDAO {
	
	public List<String> listAllProduttori(){
		String sql = "SELECT DISTINCT Ragione_Sociale_Produttore " + 
				"FROM registro_rifiuti " + 
				"ORDER BY Ragione_Sociale_Produttore ";
		List<String> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(res.getString("Ragione_Sociale_Produttore"));
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> listAllSmaltitori(){
		String sql = "SELECT DISTINCT Ragione_Sociale_Smaltitore " + 
				"FROM registro_rifiuti " + 
				"ORDER BY Ragione_Sociale_Smaltitore ";
		List<String> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(res.getString("Ragione_Sociale_Smaltitore"));
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String testoRicercaProduttori(String ragioneSoc, int meseIniziale, int meseFinale){
		String sql = "SELECT Descrizione_Europea1, AVG(Peso_Netto_Rifiuto_in_Kg) AS media " + 
				"FROM registro_rifiuti " + 
				"WHERE Ragione_Sociale_Produttore = ? " + 
				"AND Month(Data_Registrazione)>= ? AND Month(Data_Registrazione)<= ? " + 
				"GROUP BY Descrizione_Europea1 ";
		
		String testo = String.format("%-60s %-25s", "Descrizione Europea1", "Media in Kg dei rifiuti");
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, ragioneSoc);
			st.setInt(2, meseIniziale);
			st.setInt(3, meseFinale);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				testo += String.format("\n%-60s %.2f", res.getString("Descrizione_Europea1"), res.getDouble("media"));
			}
			conn.close();
			return testo;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String testoRicercaSmaltitori(String ragioneSoc, int meseIniziale, int meseFinale){
		String sql = "SELECT Descrizione_Europea1, AVG(Peso_Netto_Rifiuto_in_Kg) AS media " + 
				"FROM registro_rifiuti " + 
				"WHERE Ragione_Sociale_Smaltitore = ? " + 
				"AND Month(Data_Registrazione)>= ? AND Month(Data_Registrazione)<= ? " + 
				"GROUP BY Descrizione_Europea1 ";
		
		String testo = String.format("%-60s %-25s", "Descrizione Europea1", "Media in Kg dei rifiuti");
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, ragioneSoc);
			st.setInt(2, meseIniziale);
			st.setInt(3, meseFinale);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				testo += String.format("\n%-60s %.2f", res.getString("Descrizione_Europea1"), res.getDouble("media"));
			}
			conn.close();
			return testo;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

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

