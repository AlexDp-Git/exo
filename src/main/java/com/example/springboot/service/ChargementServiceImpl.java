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
	public Chargement createChargement(Chargement product) {
		return chargementRepository.save(product);
	}

	@Override
	public Chargement updateChargement(Chargement product) {
		Optional<Chargement> productDb = this.chargementRepository.findById(product.getId());
		
		if(productDb.isPresent()) {
			Chargement productUpdate = productDb.get();
			productUpdate.setId(product.getId());
			productUpdate.setName(product.getName());
			chargementRepository.save(productUpdate);
			return productUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + product.getId());
		}		
	}

	@Override
	public List<Chargement> getAllChargement() {
		return this.chargementRepository.findAll();
	}

	@Override
	public Chargement getChargementById(long productId) {
		
		Optional<Chargement> productDb = this.chargementRepository.findById(productId);
		
		if(productDb.isPresent()) {
			return productDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
	}

	@Override
	public void deleteChargement(long productId) {
		Optional<Chargement> productDb = this.chargementRepository.findById(productId);
		
		if(productDb.isPresent()) {
			this.chargementRepository.delete(productDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
		
	}

}
