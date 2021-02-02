package com.example.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Chargement;
import com.example.springboot.repository.ChargementRepository;

@Service
@Transactional
public class ChargementServiceImpl implements ChargementService{

	
	@Autowired
	private ChargementRepository chargementRepository;
	
	
	@Override
	public Chargement createChargement(Chargement chargement) {
		return chargementRepository.save(chargement);
	}

	@Override
	public Chargement updateChargement(Chargement chargement) {
		Optional<Chargement> chargementDb = this.chargementRepository.findById(chargement.getId());
		
		if(chargementDb.isPresent()) {
			Chargement chargementUpdate = chargementDb.get();
			chargementUpdate.setId(chargement.getId());
			chargementUpdate.setName(chargement.getName());
			chargementRepository.save(chargementUpdate);
			return chargementUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + chargement.getId());
		}		
	}

	@Override
	public List<Chargement> getAllChargement() {
		return this.chargementRepository.findAll();
	}

	@Override
	public Chargement getChargementById(long chargementId) {
		
		Optional<Chargement> chargementDb = this.chargementRepository.findById(chargementId);
		
		if(chargementDb.isPresent()) {
			return chargementDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + chargementId);
		}
	}

	@Override
	public void deleteChargement(long chargementId) {
		Optional<Chargement> chargementDb = this.chargementRepository.findById(chargementId);
		
		if(chargementDb.isPresent()) {
			this.chargementRepository.delete(chargementDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + chargementId);
		}
		
	}

}
