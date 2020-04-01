package it.polito.tdp.lab04.DAO;

import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		List<Corso> corsi = cdao.getTuttiICorsi();
		
		
		StudenteDAO sdao= new StudenteDAO();
		sdao.getStudente(200463);
		
		System.out.println("-----------Studenti------------");
		List<Studente> studenti = cdao.getStudentiIscrittiAlCorso(corsi.get(2));
		/*for(Studente s : studenti)
			System.out.println(s);*/
		cdao.getCorso("01KSUPG");
		cdao.getCorsiStudente(studenti.get(2));
		
	}

}
