package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Model {
	
	private CorsoDAO dao;
	
	public Model() {
		this.dao = new CorsoDAO();
	}

	public List<Corso> getTuttiICorsi(){
		return this.dao.getTuttiICorsi();
	}
}
