package com.example.springboot.service;

import java.util.List;

import com.example.springboot.model.Camion;
import com.example.springboot.model.Expedition;

public interface ExpeditionService {
	/**
	 * CRUD Create
	 * @param expedition
	 * @return Expedition
	 */
	Expedition createExpedition(Expedition expedition);

	/**
	 * CRUD Update
	 * @param expedition
	 * @return new Expedition
	 */
	Expedition updateExpedition(Expedition expedition);

	/**
	 * CRUD Read
	 * @return List of All Expedition
	 */
	List<Expedition> getAllExpedition();

	/**
	 * CRUD Read
	 * @param tracteurid
	 * @param remorqueid
	 * @return Expedition get by TracteurId and RemorqueId
	 */
	Expedition getExpeditionByTracteurIdAndRemorqueId(long tracteurid, long remorqueid);
	
	/**
	 * CRUD Delete
	 * @param tracteurId
	 * @param remorqueId
	 */
	void deleteExpeditionById(long tracteurId, long remorqueId);

	/**
	 * Function to return all the Camion in a Expedition
	 * @return
	 */
	List<Camion> getCamionFromExpedition();
}
