package it.polito.tdp.trasportoRifiuti;

import java.net.URL;
import java.time.Month;
import java.util.ResourceBundle;

import it.polito.tdp.trasportoRifiuti.model.Model;
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
    private ComboBox<?> cmbDescrizione;

    @FXML
    private ComboBox<?> cmbTrasportatore;

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
    private TextArea txtRisultati;

    @FXML
    void associaProduttori(ActionEvent event) {
    	this.cmbRagioneSociale.getItems().clear();
    	this.cmbRagioneSociale.getItems().setAll(this.model.getProduttori());
    }

    @FXML
    void associaSmaltitori(ActionEvent event) {
    	this.cmbRagioneSociale.getItems().clear();
    	this.cmbRagioneSociale.getItems().setAll(this.model.getSmaltitori());
    }

    @FXML
    void doInserimento(ActionEvent event) {

    }

    @FXML
    void doPulizia(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {

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
        assert txtRisultati != null : "fx:id=\"txtRisultati\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbRagioneSociale.getItems().setAll(this.model.getProduttori());
    	for(int i = 1; i<=12; i++) {
    	    this.cmbMeseInizio.getItems().add(Month.of(i));
    	    this.cmbMeseFine.getItems().add(Month.of(i));
    	}
    }
}
