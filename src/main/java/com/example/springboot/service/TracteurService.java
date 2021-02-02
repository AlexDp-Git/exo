package com.example.springboot.service;

import java.util.List;

import com.example.springboot.model.Tracteur;

public interface TracteurService {
	/**
	 * CRUD Create
	 * @param tracteur
	 * @return Tracteur
	 */
	Tracteur createTracteur(Tracteur tracteur);

	/**
	 * CRUD Update
	 * @param tracteur
	 * @return Tracteur
	 */
	Tracteur updateTracteur(Tracteur tracteur);

	/**
	 * CRUD Read 
	 * @return List of all Tracteur
	 */
	List<Tracteur> getAllTracteur();

	/**
	 * CRUD Read
	 * @param tracteurId
	 * @return Tracteur get by Id
	 */
	Tracteur getTracteurById(long tracteurId);

	/**
	 * CRUD Delete
	 * @param id
	 */
	void deleteTracteur(long id);
}
