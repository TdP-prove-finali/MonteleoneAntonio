package it.polito.tdp.trasportoRifiuti;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.trasportoRifiuti.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private ComboBox<?> cmbRagione;

    @FXML
    private ComboBox<?> cmbMeseInizio;

    @FXML
    private ComboBox<?> cmbMeseFine;

    @FXML
    private Button btnCerca;

    @FXML
    private ToggleGroup ragioneSociale;

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

    }

    @FXML
    void doSimulazione(ActionEvent event) {

    }

    @FXML
    void i(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert cmbRagione != null : "fx:id=\"cmbRagione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMeseInizio != null : "fx:id=\"cmbMeseInizio\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMeseFine != null : "fx:id=\"cmbMeseFine\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert ragioneSociale != null : "fx:id=\"ragioneSociale\" was not injected: check your FXML file 'Scene.fxml'.";
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
    }
}

