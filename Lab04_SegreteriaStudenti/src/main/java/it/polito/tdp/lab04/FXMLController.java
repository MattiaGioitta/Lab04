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
	private List<Corso> corsiCaricati;
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
    	this.txtNome.clear();
    	this.txtCognome.clear();
    	try {
    	String scelto = (String) choiceBox.getValue();
    	String matr=this.txtMatricola.getText();
    	int matricola = Integer.parseInt(matr);
    	Studente s = this.studente.getStudente(matricola);
    	if(s==null) {
    		txtRisultato.appendText("Errore: Matricola non presente!");
    		return;
    	} 	
    	List<Corso> corsi = this.corso.getCorsiStudente(s);
    	StringBuilder sb = new StringBuilder();

		for (Corso corso : corsi) {
			sb.append(String.format("%-8s ", corso.getCodins()));
			sb.append(String.format("%-4s ", corso.getNumeroCrediti()));
			sb.append(String.format("%-45s ", corso.getNome()));
			sb.append(String.format("%-4s ", corso.getPeriodoDidattico()));
			sb.append("\n");
		}
		txtRisultato.appendText(sb.toString());
    	
    	
        	
    		
    	}catch(NumberFormatException ec) {
    		this.txtRisultato.appendText("Formato della matricola non corretto!");
    	}catch(RuntimeException r) {
    		txtRisultato.setText("ERRORE DI CONNESSIONE AL DATABASE!");
    	}
    	
    	

    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	this.txtRisultato.clear();
    	try {
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
    	StringBuilder sb = new StringBuilder();

		for (Studente studente : studenti) {

			sb.append(String.format("%-10s ", studente.getMatricola()));
			sb.append(String.format("%-20s ", studente.getCognome()));
			sb.append(String.format("%-20s ", studente.getNome()));
			sb.append(String.format("%-10s ", studente.getCDS()));
			sb.append("\n");
		}

		txtRisultato.appendText(sb.toString());
        }catch(RuntimeException r) {
    		txtRisultato.setText("ERRORE DI CONNESSIONE AL DATABASE!"); 
    	}
    		
    	

    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	this.txtRisultato.clear();
    	try{String scelto = (String) choiceBox.getValue();
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
    	String matr=this.txtMatricola.getText();
    	for(int i=0; i<matr.length();i++) {
    		Character c = matr.charAt(i);
    		if(!Character.isDigit(c)) {
    			this.txtRisultato.appendText("Matricola non valida! Matricola formata solo da numeri");
    			return;
    		}
    	}
    	int matricola = Integer.parseInt(matr);
    	
    	Studente s = this.studente.getStudente(matricola);
    	if(s==null) {
    	    txtRisultato.appendText("Errore: studente non iscritto!");
    	    return;
    	    }
    	if (model.isStudenteIscrittoACorso(s, corsoScelto)) {
			txtRisultato.appendText("Studente già iscritto a questo corso");
			return;
		}
		
		if (!model.inscriviStudenteACorso(s, corsoScelto)) {
			txtRisultato.appendText("Errore durante l'iscrizione al corso");
			return;
		} else {
			txtRisultato.appendText("Studente iscritto al corso!");
		}
    	}catch(RuntimeException r) {
    		this.txtRisultato.appendText("ERRORE CON IL DATABASE");
    	}
    	
    }
    
    @FXML
    void doCompleta(ActionEvent event) {
    	this.txtRisultato.clear();
    	this.txtNome.clear();
    	this.txtNome.clear();
    	try {
    	int matricola = Integer.parseInt(this.txtMatricola.getText());
    	Studente s = this.studente.getStudente(matricola);
    	if(s==null) {
    	    txtRisultato.appendText("Errore: matricola non presente!");
    	    return;
    	    }
    	this.txtCognome.setText(s.getCognome());
    	this.txtNome.setText(s.getNome());
    	}catch(NumberFormatException ec) {
    		this.txtRisultato.appendText("Formato della matricola non corretto!");
    	}catch(RuntimeException r) {
    		txtRisultato.setText("ERRORE DI CONNESSIONE AL DATABASE!"); 
    	}

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
		this.loadData();
	}
}
