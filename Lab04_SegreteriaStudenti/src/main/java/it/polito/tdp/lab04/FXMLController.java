/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	CorsoDAO corso = new CorsoDAO();
	StudenteDAO studente = new StudenteDAO();
	ObservableList corsi = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<?> choiceBox;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private TextField txtMatricola;
    
    @FXML
    private Button btnCompleta;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnReset;

    @FXML
    /**
     * metodo che dato uno studente ritorna tutti i corsi
     * a cui questo studente è iscritto
     * se non iscritto manda un messaggio di errore
     * @param event
     */
    void doCercaCorsi(ActionEvent event) {
    	this.txtRisultato.clear();
    	String scelto = (String) choiceBox.getValue();
    	int matricola = Integer.parseInt(this.txtMatricola.getText());
    	Studente s = this.studente.getStudente(matricola);
    	if(s==null) {
    		txtRisultato.appendText("Errore!");
    	}
    	if(scelto.compareTo("Corsi")==0) {    	
    	List<Corso> corsi = this.corso.getCorsiStudente(s);
    	for(Corso c : corsi) {
    		this.txtRisultato.appendText(c.toString()+"\n");
    	   }
    	}
    	else {
    		Corso corsoScelto = null;
        	for(Corso c : this.corso.getTuttiICorsi()) {
        		if(c.getNome().compareTo(scelto)==0) {
        			corsoScelto = c;
        		}
        	}
        	List<Studente> studenti = this.corso.getStudentiIscrittiAlCorso(corsoScelto);
        	for(Studente st : studenti) {
        		if(st.equals(s)) {
        			this.txtRisultato.appendText("Studente già iscritto al corso!");
        			return ;
        		}
        		else {
        			this.txtRisultato.appendText("Studente non iscritto al corso!");
        			return ;
        		}
        		
        	}
        	
    		
    	}
    	
    	

    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	this.txtRisultato.clear();
    	String scelto = (String) choiceBox.getValue();
    	if(scelto.compareTo("Corsi")==0) {
    		this.txtRisultato.appendText("Errore: scegli un corso della lista!");
    		return;
    	}
    	Corso corsoScelto = null;
    	for(Corso c : this.corso.getTuttiICorsi()) {
    		if(c.getNome().compareTo(scelto)==0) {
    			corsoScelto = c;
    		}
    	}
    	List<Studente> studenti = this.corso.getStudentiIscrittiAlCorso(corsoScelto);
    	for(Studente s : studenti) {
    		this.txtRisultato.appendText(s.toString()+"\n");
    	}
    		
    	

    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }
    
    @FXML
    void doCompleta(ActionEvent event) {
    	int matricola = Integer.parseInt(this.txtMatricola.getText());
    	Studente s = this.studente.getStudente(matricola);
    	if(s==null) {
    	    txtRisultato.appendText("Errore: studente non iscritto!");
    	    return;
    	    }
    	this.txtCognome.setText(s.getCognome());
    	this.txtNome.setText(s.getNome());
    	

    }

    @FXML
    void doReset(ActionEvent event) {
    	this.txtCognome.clear();
    	this.txtNome.clear();
    	this.txtMatricola.clear();
    	this.txtRisultato.clear();
    	

    }
    
    public void loadData() {
    	List<String> nomiCorsi = new ArrayList<String>();
    	nomiCorsi.add("Corsi");
    	for(Corso c : corso.getTuttiICorsi()) {
    		nomiCorsi.add(c.getNome());
    	}
    	corsi.addAll(nomiCorsi);
    	choiceBox.setItems(this.corsi);
    	}

    @FXML
    void initialize() {
        this.loadData();
        assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCompleta != null : "fx:id=\"btnCompleta\" was not injected: check your FXML file 'Scene.fxml'.";
        
    }

	public void setModel(Model model) {
		this.model = model;
		
	}
}
