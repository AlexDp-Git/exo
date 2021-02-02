package com.example.springboot.service;

import java.util.List;

import com.example.springboot.model.Chauffeur;

public interface ChauffeurService {
	/**
	 * CRUD Create 
	 * @param chauffeur
	 * @return Chauffeur
	 */
	Chauffeur createChauffeur(Chauffeur chauffeur);

	/**
	 * CRUD Update
	 * @param chauffeur
	 * @return new Chauffeur
	 */
	Chauffeur updateChauffeur(Chauffeur chauffeur);

	/**
	 * CRUD Read
	 * @return List of all chauffeur
	 */
	List<Chauffeur> getAllChauffeur();

	/**
	 * CRUD Read by ID 
	 * @param productId
	 * @return Chauffeur get by Id 
	 */
	Chauffeur getChauffeurById(long productId);

	/**
	 * CRUD Delete 
	 * @param id
	 */
	void deleteChauffeur(long id);
}
