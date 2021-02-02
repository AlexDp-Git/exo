package com.example.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Tracteur;
import com.example.springboot.repository.TracteurRepository;

@Service
@Transactional
public class TracteurServiceImpl implements TracteurService{

	
	@Autowired
	private TracteurRepository tracteurRepository;
	
	
	@Override
	public Tracteur createTracteur(Tracteur product) {
		return tracteurRepository.save(product);
	}

	@Override
	public Tracteur updateTracteur(Tracteur product) {
		Optional<Tracteur> productDb = this.tracteurRepository.findById(product.getId());
		
		if(productDb.isPresent()) {
			Tracteur productUpdate = productDb.get();
			productUpdate.setId(product.getId());
			productUpdate.setName(product.getName());
			tracteurRepository.save(productUpdate);
			return productUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + product.getId());
		}		
	}

	@Override
	public List<Tracteur> getAllTracteur() {
		return this.tracteurRepository.findAll();
	}

	@Override
	public Tracteur getTracteurById(long productId) {
		
		Optional<Tracteur> productDb = this.tracteurRepository.findById(productId);
		
		if(productDb.isPresent()) {
			return productDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
	}

	@Override
	public void deleteTracteur(long productId) {
		Optional<Tracteur> productDb = this.tracteurRepository.findById(productId);
		
		if(productDb.isPresent()) {
			this.tracteurRepository.delete(productDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
		
	}

}
