package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
	}

	public List<Corso> getTuttiICorsi(){
		return this.corsoDAO.getTuttiICorsi();
	}
	public boolean isStudenteIscrittoACorso(Studente studente, Corso corso) {
		return studenteDAO.isStudenteIscrittoACorso(studente,corso);
	}
	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		return corsoDAO.inscriviStudenteACorso(studente, corso);
}
}
