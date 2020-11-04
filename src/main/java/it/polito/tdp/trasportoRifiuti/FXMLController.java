package it.polito.tdp.trasportoRifiuti;

import java.net.URL;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import it.polito.tdp.trasportoRifiuti.model.MezzoDiTrasporto;
import it.polito.tdp.trasportoRifiuti.model.Model;
import it.polito.tdp.trasportoRifiuti.model.Registrazione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class FXMLController {
	
	private Model model;
	private boolean flag = true;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbRagioneSociale;

    @FXML
    private ComboBox<Month> cmbMeseInizio;

    @FXML
    private ComboBox<Month> cmbMeseFine;

    @FXML
    private Button btnCerca;

    @FXML
    private RadioButton rbtnSmaltitore;

    @FXML
    private ToggleGroup ragioneSociale;

    @FXML
    private RadioButton rbtnProduttore;

    @FXML
    private ComboBox<String> cmbDescrizione;

    @FXML
    private ComboBox<String> cmbTrasportatore;

    @FXML
    private TextField txtQta;

    @FXML
    private TextField txtCapienza;

    @FXML
    private Button btnAggiungi;

    @FXML
    private Button btnResetta;

    @FXML
    private TextField txtProbabilita;

    @FXML
    private TextField txtPercentuale;

    @FXML
    private TextField txtMaxSpostamenti;

    @FXML
    private Button btnAvviaSimulazione;

    @FXML
    private Button btnPulisci;
    
    @FXML
    private Button btnVisualizza;

    @FXML
    private TextArea txtRisultati;

    @FXML
    void associaProduttori(ActionEvent event) {
    	this.cmbRagioneSociale.getItems().clear();
    	this.cmbRagioneSociale.getItems().addAll(this.model.getProduttori());
    }

    @FXML
    void associaSmaltitori(ActionEvent event) {
    	this.cmbRagioneSociale.getItems().clear();
    	this.cmbRagioneSociale.getItems().addAll(this.model.getSmaltitori());
    }
    
    @FXML
    void associaTrasportatori(ActionEvent event) {
    	String descrizione  = this.cmbDescrizione.getValue();
    	this.cmbTrasportatore.getItems().clear();
    	this.cmbTrasportatore.getItems().addAll(this.model.getTrasportatori(descrizione));
    	this.model.creaListaMezziDiTrasporto();
    	if(flag==false) {
    		this.txtRisultati.setText("Cambiando Descrizione Europea1 eventuali mezzi di trasporto inseriti\ned associati alla Descrizione Europea1 precedente\nsono stati eliminati");	
    	}
    	this.flag = false;
    	
    }

    @FXML
    void doInserimento(ActionEvent event) {
    	
    	String descrizione = this.cmbDescrizione.getValue();
    	
    	if(descrizione==null) {
    		this.txtRisultati.setText("Non puoi aggiungere i mezzi se prima non selezioni una Descrizione Europea1");
    		return;
    	}
    	
    	String trasportatore = this.cmbTrasportatore.getValue();
    	
    	if(trasportatore==null) {
    		this.txtRisultati.setText("Non puoi aggiungere i mezzi se prima non selezioni una ragione sociale Trasportatore");
    		return;
    	}
    	
    	int quantita;
    	
    	try {
    	    		
    	    quantita = Integer.parseInt(this.txtQta.getText());
    	    	    		
    	}catch(NumberFormatException ne) {
    	    this.txtRisultati.setText("Formato quantità errato\ndeve essere un numero intero maggiore di zero");
    	    return;
    	}
    	
    	if(quantita<=0) {
    		this.txtRisultati.setText("La quanità inserita deve essere un numero intero maggiore di zero");
     	    return;
    	}
    	
    	int capienza;
    	
    	try {
    	    		
    	    capienza = Integer.parseInt(this.txtCapienza.getText());
    	    	    		
    	}catch(NumberFormatException ne) {
    	    this.txtRisultati.setText("Formato capienza errato\ndeve essere un numero intero maggiore di zero");
    	    return;
    	}
    	
    	if(capienza<=0) {
    		this.txtRisultati.setText("La capienza inserita deve essere un numero intero maggiore di zero");
     	    return;
    	}
    	
    	this.model.aggiungiMezziDiTrasporto(trasportatore, quantita, capienza);
    	
    	this.txtQta.clear();
    	this.txtCapienza.clear();
    	this.txtRisultati.setText("Aggiunti -mezzi: "+quantita+" -capienza: "+capienza+" -trasportatore: "+trasportatore);
    	
    	

    }

    @FXML
    void doPulizia(ActionEvent event) {
    	this.txtRisultati.clear();
    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	String descrizione = this.cmbDescrizione.getValue();
    	
    	if(descrizione==null) {
    		this.txtRisultati.setText("Non hai aggiunto nulla\ndevi prima selezionare una Descrizione Europea1");
    		return;
    	}
    	
    	String trasportatore = this.cmbTrasportatore.getValue();
    	
    	if(trasportatore==null) {
    		this.txtRisultati.setText("Non puoi resettare i mezzi inseriti se prima non selezioni una ragione sociale Trasportatore");
    		return;
    	}
    	
    	this.model.cancellaMezziDiTrasporto(trasportatore);
    	
    	this.txtRisultati.setText("Cancellati mezzi di trasporto per trasportatore: "+trasportatore+"\nora potrai inserirli nuovamente");

    }

    @FXML
    void doRicerca(ActionEvent event) {
    	String ragioneSoc = this.cmbRagioneSociale.getValue();
    	
    	if(ragioneSoc==null) {
    		this.txtRisultati.setText("Impossibile effettuare la ricerca\ndevi prima selezionare una ragioneSociale");
    		return;
    	}
    	
    	Month meseI = this.cmbMeseInizio.getValue();
    	Month meseF = this.cmbMeseFine.getValue();
    	
    	if(meseI==null) {
    		this.txtRisultati.setText("Impossibile effettuare la ricerca\ndevi prima selezionare un mese iniziale");
    		return;
    	}
    	
    	if(meseF==null) {
    		this.txtRisultati.setText("Impossibile effettuare la ricerca\ndevi prima selezionare un mese finale");
    		return;
    	}
    	
    	int meseIniziale = meseI.getValue();
    	int meseFinale = meseF.getValue();
    	
    	if(meseFinale<meseIniziale) {
    		this.txtRisultati.setText("Impossibile effettuare la ricerca\nil mese finale deve essere uguale o successivo al mese iniziale");
    		return;
    	}
    	
    	RadioButton radioRagioneSociale = (RadioButton) this.ragioneSociale.getSelectedToggle();
    	String sceltaRagioneSociale = radioRagioneSociale.getText();
    	
    	String testo = "";
    	
    	if(sceltaRagioneSociale.equals(this.rbtnProduttore.getText())) {
    		testo = this.model.getRicercaProduttori(ragioneSoc, meseIniziale, meseFinale);
    	}else if(sceltaRagioneSociale.equals(this.rbtnSmaltitore.getText())){
    		testo = this.model.getRicercaSmaltitori(ragioneSoc, meseIniziale, meseFinale);
    	}else {
    		this.txtRisultati.setText("Impossibile effettuare la ricerca\nsi è verificato un errore sconosciuto\nprovare a ripetere la procedura");
    		return;
    	}
    	
    	this.txtRisultati.setText(testo);

    }

    @FXML
    void doSimulazione(ActionEvent event) {
    	
    	String descrizione = this.cmbDescrizione.getValue();
    	
    	if(descrizione==null) {
    		this.txtRisultati.setText("Impossibile effettuare la simulazione\ndevi prima selezionare una Descrizione Europea1");
    		return;
    	}
    	
    	int probabilita;
    	
    	try {
    	    		
    	    probabilita = Integer.parseInt(this.txtProbabilita.getText());
    	    	    		
    	}catch(NumberFormatException ne) {
    	    this.txtRisultati.setText("Formato probabilita errato\ndeve essere un numero intero compreso tra zero e cento");
    	    return;
    	}
    	
    	if(probabilita<0 || probabilita>100) {
    		this.txtRisultati.setText("La probabilita inserita deve essere un numero intero compreso tra zero e cento");
     	    return;
    	}
    	
    	int percentuale;
    	
    	try {
    	    		
    	    percentuale = Integer.parseInt(this.txtPercentuale.getText());
    	    	    		
    	}catch(NumberFormatException ne) {
    	    this.txtRisultati.setText("Formato percentuale errato\ndeve essere un numero intero maggiore di uno");
    	    return;
    	}
    	
    	if(percentuale<1) {
    		this.txtRisultati.setText("La percentuale inserita deve essere un numero intero maggiore di uno");
     	    return;
    	}
    	
    	int max;
    	
    	try {
    	    		
    	    max = Integer.parseInt(this.txtMaxSpostamenti.getText());
    	    	    		
    	}catch(NumberFormatException ne) {
    	    this.txtRisultati.setText("Formato max spostamenti errato\ndeve essere un numero intero maggiore di uno");
    	    return;
    	}
    	
    	if(max<=0) {
    		this.txtRisultati.setText("Il numero max spostamenti inserito deve essere un numero intero maggiore di uno");
     	    return;
    	}
    	
    	if(this.model.verificaInserimento()==false) {
    		this.txtRisultati.setText("Impossibile effettuare la simulazione\nnon hai aggiunto mezzi per ogni ragione sociale Trasportatore");
    		return;
    	}
    	
    	this.txtRisultati.clear();
    	
    	this.model.simula(descrizione,probabilita,percentuale,max);
    	
    	Map<String,Integer> zone = new TreeMap<>(this.model.getZone());
    	
    	this.txtRisultati.appendText(String.format("%-40s %-25s\n", "ZONA DI RACCOLTA", "RIFIUTI NON TRASPORTATI in Kg"));
    	
    	for(String z: zone.keySet()) {
    		this.txtRisultati.appendText(String.format("%-40s %-25s\n", z,zone.get(z)));
    	}
    	
    	this.txtRisultati.appendText(String.format("\n\n%-68s %-15s\n", "MEZZO DI TRASPORTO", "GIORNI DI INATTIVITA'"));
    	
    	Map<MezzoDiTrasporto,Integer> inattivi = new TreeMap<>(this.model.getInattivi());
    	
    	for(MezzoDiTrasporto m: inattivi.keySet()) {
    		this.txtRisultati.appendText(String.format("%-68s %-15s\n", m,inattivi.get(m)));
    	}
    	

    }
    
    @FXML
    void visualizzaElenco(ActionEvent event) {
    	
    	String descrizione = this.cmbDescrizione.getValue();
    	
    	if(descrizione==null) {
    		this.txtRisultati.setText("Non hai aggiunto nessun mezzo\ndevi prima selezionare una Descrizione Europea1");
    		return;
    	}
    	
    	if(this.model.getMezzi().size()==0) {
    		this.txtRisultati.setText("Non hai aggiunto nessun mezzo per la Descrizione Europea1 selezionata");
    		return;
    	}
    	
    	this.txtRisultati.clear();
    	for(MezzoDiTrasporto m: this.model.getMezzi()) {
    		this.txtRisultati.appendText("Mezzo di trasporto "+m.toString()+"\n");
    	}

    }

    @FXML
    void initialize() {
        assert cmbRagioneSociale != null : "fx:id=\"cmbRagioneSociale\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMeseInizio != null : "fx:id=\"cmbMeseInizio\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMeseFine != null : "fx:id=\"cmbMeseFine\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert rbtnSmaltitore != null : "fx:id=\"rbtnSmaltitore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert ragioneSociale != null : "fx:id=\"ragioneSociale\" was not injected: check your FXML file 'Scene.fxml'.";
        assert rbtnProduttore != null : "fx:id=\"rbtnProduttore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbDescrizione != null : "fx:id=\"cmbDescrizione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbTrasportatore != null : "fx:id=\"cmbTrasportatore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtQta != null : "fx:id=\"txtQta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCapienza != null : "fx:id=\"txtCapienza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAggiungi != null : "fx:id=\"btnAggiungi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnResetta != null : "fx:id=\"btnResetta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtProbabilita != null : "fx:id=\"txtProbabilita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtPercentuale != null : "fx:id=\"txtPercentuale\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMaxSpostamenti != null : "fx:id=\"txtMaxSpostamenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAvviaSimulazione != null : "fx:id=\"btnAvviaSimulazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPulisci != null : "fx:id=\"btnPulisci\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnVisualizza != null : "fx:id=\"btnVisualizza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultati != null : "fx:id=\"txtRisultati\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbRagioneSociale.getItems().addAll(this.model.getProduttori());
    	for(int i = 1; i<=12; i++) {
    	    this.cmbMeseInizio.getItems().add(Month.of(i));
    	    this.cmbMeseFine.getItems().add(Month.of(i));
    	}
    	this.cmbDescrizione.getItems().addAll(this.model.getDescrizioni());
    }
}
