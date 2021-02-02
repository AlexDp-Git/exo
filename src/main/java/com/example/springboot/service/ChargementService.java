package com.example.springboot.service;

import java.util.List;

import com.example.springboot.model.Chargement;

public interface ChargementService {
	/**
	 * CRUD Create
	 * @param chargement
	 * @return chargement
	 */
	Chargement createChargement(Chargement chargement);

	/**
	 * CRUD Update
	 * @param chargement
	 * @return new Chargement
	 */
	Chargement updateChargement(Chargement chargement);

	/**
	 * CRUD Read
	 * @return List of Chargement
	 */
	List<Chargement> getAllChargement();

	/**
	 * CRUD Read by Id
	 * @param chargementId
	 * @return Chargement
	 */
	Chargement getChargementById(long chargementId);

	/**
	 * CRUD Delete
	 * @param id
	 */
	void deleteChargement(long id);
}
