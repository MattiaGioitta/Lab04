package it.polito.tdp.lab04.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		/*
		 * 	Write here your test model
		 */

		List<Corso> l = model.getTuttiICorsi();
		for(Corso c : l) {
			System.out.println(c);
		}
	}

}
