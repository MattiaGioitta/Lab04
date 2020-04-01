package it.polito.tdp.lab04.model;

public class Corso {
	private String codins;
	private int numeroCrediti;
	private String nome;
	private int periodoDidattico;
	/**
	 * @param codins
	 * @param numeroCrediti
	 * @param nome
	 * @param periodoDidattico
	 */
	public Corso(String codins, int numeroCrediti, String nome, int periodoDidattico) {
		super();
		this.codins = codins;
		this.numeroCrediti = numeroCrediti;
		this.nome = nome;
		this.periodoDidattico = periodoDidattico;
	}
	/**
	 * @return the codins
	 */
	public String getCodins() {
		return codins;
	}
	/**
	 * @param codins the codins to set
	 */
	public void setCodins(String codins) {
		this.codins = codins;
	}
	/**
	 * @return the numeroCrediti
	 */
	public int getNumeroCrediti() {
		return numeroCrediti;
	}
	/**
	 * @param numeroCrediti the numeroCrediti to set
	 */
	public void setNumeroCrediti(int numeroCrediti) {
		this.numeroCrediti = numeroCrediti;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the periodoDidattico
	 */
	public int getPeriodoDidattico() {
		return periodoDidattico;
	}
	/**
	 * @param periodoDidattico the periodoDidattico to set
	 */
	public void setPeriodoDidattico(int periodoDidattico) {
		this.periodoDidattico = periodoDidattico;
	}
	@Override
	public String toString() {
		return "Corso [codins=" + codins + ", numeroCrediti=" + numeroCrediti + ", nome=" + nome + ", periodoDidattico="
				+ periodoDidattico + "]";
	}
	
	
	

}
