package com.example.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Chauffeur;
import com.example.springboot.repository.ChauffeurRepository;

@Service
@Transactional
public class ChauffeurServiceImpl implements ChauffeurService{

	
	@Autowired
	private ChauffeurRepository chauffeurRepository;
	
	
	@Override
	public Chauffeur createChauffeur(Chauffeur product) {
		return chauffeurRepository.save(product);
	}

	@Override
	public Chauffeur updateChauffeur(Chauffeur product) {
		Optional<Chauffeur> productDb = this.chauffeurRepository.findById(product.getId());
		
		if(productDb.isPresent()) {
			Chauffeur productUpdate = productDb.get();
			productUpdate.setId(product.getId());
			productUpdate.setName(product.getName());
			chauffeurRepository.save(productUpdate);
			return productUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + product.getId());
		}		
	}

	@Override
	public List<Chauffeur> getAllChauffeur() {
		return this.chauffeurRepository.findAll();
	}

	@Override
	public Chauffeur getChauffeurById(long productId) {
		
		Optional<Chauffeur> productDb = this.chauffeurRepository.findById(productId);
		
		if(productDb.isPresent()) {
			return productDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
	}

	@Override
	public void deleteChauffeur(long productId) {
		Optional<Chauffeur> productDb = this.chauffeurRepository.findById(productId);
		
		if(productDb.isPresent()) {
			this.chauffeurRepository.delete(productDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
		
	}

}
