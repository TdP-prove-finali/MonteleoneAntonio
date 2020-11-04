package it.polito.tdp.trasportoRifiuti.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.trasportoRifiuti.model.Registrazione;


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
		
		String testo = String.format("%-60s %-25s", "DESCRIZIONE EUROPEA1", "MEDIA in Kg RIFIUTI");
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
		
		String testo = String.format("%-60s %-25s", "DESCRIZIONE EUROPEA1", "MEDIA in Kg RIFIUTI");
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
	

	public List<String> listAllDescrizioni(){
		String sql = "SELECT distinct Descrizione_Europea1 " + 
				"FROM registro_rifiuti " + 
				"ORDER BY Descrizione_Europea1 ";
		List<String> result = new ArrayList<String>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(res.getString("Descrizione_Europea1"));
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> listAllTrasportatori(String descrizione){
		String sql = "SELECT DISTINCT Ragione_Sociale_Trasportatore " + 
				"FROM registro_rifiuti " + 
				"WHERE Descrizione_Europea1 = ? " + 
				"ORDER BY Ragione_Sociale_Trasportatore ";
		
		List<String> result = new ArrayList<String>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, descrizione);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(res.getString("Ragione_Sociale_Trasportatore"));
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Registrazione> listAllRegistrazioni(String descrizione){
		String sql = "SELECT Data_Registrazione, Ragione_Sociale_Trasportatore, Zona_di_raccolta, SUM(Peso_Netto_Rifiuto_in_Kg) AS Peso " + 
				"FROM registro_rifiuti " + 
				"WHERE Descrizione_Europea1 = ? " + 
				"GROUP BY Data_Registrazione, Ragione_Sociale_Trasportatore, Zona_di_raccolta " + 
				"ORDER BY Data_Registrazione ";
		
		List<Registrazione> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, descrizione);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				String zona = res.getString("Zona_di_raccolta");
				if(zona==null) {
					zona = "ZONA NON DEFINITA";
				}
				Registrazione reg = new Registrazione(res.getDate("Data_Registrazione").toLocalDate(), res.getString("Ragione_Sociale_Trasportatore"), zona, res.getInt("Peso"));
				//System.out.println(reg.toString()+"\n");
				result.add(reg);
				
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> listAllZoneDiRaccolta(String descrizione){
		String sql = "SELECT DISTINCT Zona_di_raccolta " + 
				"FROM registro_rifiuti " + 
				"WHERE Descrizione_Europea1 = ? " + 
				"ORDER BY Zona_di_raccolta";
		
		List<String> result = new ArrayList<String>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, descrizione);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				String zona = res.getString("Zona_di_raccolta");
				if(zona==null) {
					zona = "ZONA NON DEFINITA";
				}
				result.add(zona);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
}

