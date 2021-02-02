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
	public Tracteur createTracteur(Tracteur tracteur) {
		return tracteurRepository.save(tracteur);
	}

	@Override
	public Tracteur updateTracteur(Tracteur tracteur) {
		Optional<Tracteur> tracteurDb = this.tracteurRepository.findById(tracteur.getId());
		
		if(tracteurDb.isPresent()) {
			Tracteur tracteurUpdate = tracteurDb.get();
			tracteurUpdate.setId(tracteur.getId());
			tracteurUpdate.setName(tracteur.getName());
			tracteurRepository.save(tracteurUpdate);
			return tracteurUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + tracteur.getId());
		}		
	}

	@Override
	public List<Tracteur> getAllTracteur() {
		return this.tracteurRepository.findAll();
	}

	@Override
	public Tracteur getTracteurById(long tracteurId) {
		
		Optional<Tracteur> tracteurDb = this.tracteurRepository.findById(tracteurId);
		
		if(tracteurDb.isPresent()) {
			return tracteurDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + tracteurId);
		}
	}

	@Override
	public void deleteTracteur(long tracteurId) {
		Optional<Tracteur> tracteurDb = this.tracteurRepository.findById(tracteurId);
		
		if(tracteurDb.isPresent()) {
			this.tracteurRepository.delete(tracteurDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + tracteurId);
		}
		
	}

}
