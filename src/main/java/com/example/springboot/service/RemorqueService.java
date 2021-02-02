package com.example.springboot.service;

import java.util.List;

import com.example.springboot.model.Remorque;

public interface RemorqueService {
	/**
	 * CRUD Create
	 * @param remorque
	 * @return Remorque 
	 */
	Remorque createRemorque(Remorque remorque);

	/**
	 * CRUD Update
	 * @param remorque
	 * @return Remorque
	 */
	Remorque updateRemorque(Remorque remorque);

	/**
	 * CRUD Read
	 * @return List of all Remorque
	 */
	List<Remorque> getAllRemorque();

	/**
	 * CRUD Read
	 * @param productId
	 * @return Remorque get by Id
	 */
	Remorque getRemorqueById(long remorqueId);

	/**
	 * CRUD delete
	 * @param id
	 */
	void deleteRemorque(long id);
}
