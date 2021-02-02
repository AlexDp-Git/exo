package com.example.springboot.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


public class Camion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="id_tracteur", unique=true, referencedColumnName = "id")
	private Tracteur tracteur;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="id_remorque", unique=true, referencedColumnName = "id")
	private Remorque remorque;


	public Tracteur getTracteur() {
		return tracteur;
	}

	public void setTracteur(Tracteur tracteur) {
		this.tracteur = tracteur;
	}

	public Remorque getRemorque() {
		return remorque;
	}

	public void setRemorque(Remorque remorque) {
		this.remorque = remorque;
	}
	
	
	
	
}
