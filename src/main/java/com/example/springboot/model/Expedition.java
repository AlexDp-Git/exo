package com.example.springboot.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "expeditions")
public class Expedition {
	
	public Expedition() {
		super();
	}
	@OneToOne
	@JoinColumn(name="id_chauffeur", unique=true, referencedColumnName="id")
	private Chauffeur chauffeur;
	
	@OneToOne
	@JoinColumn(name="id_chargement", unique=true, referencedColumnName="id")
	private Chargement chargement;
	
	@EmbeddedId
	private Camion id;


	public Chauffeur getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}


	public Chargement getChargement() {
		return chargement;
	}

	public void setChargement(Chargement chargement) {
		this.chargement = chargement;
	}

	public Camion getId() {
		return id;
	}

	public void setId(Camion id) {
		this.id = id;
	}
	
	

	
	
}
