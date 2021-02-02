package com.example.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chauffeurs")
public class Chauffeur {
	public Chauffeur(){
		super();
	}
	
	public Chauffeur(long id, String name, String lastname) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
	}
	
	public Chauffeur(String name, String lastname) {
		this.name = name;
		this.lastname = lastname;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "firstname")
	private String name;
	
	@Column(name = "lastname")
	private String lastname; 
	
	@OneToOne(mappedBy="chauffeur")
	private Expedition expedition; 
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
}
