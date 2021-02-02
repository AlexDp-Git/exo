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
	public Chauffeur createChauffeur(Chauffeur chauffeur) {
		return chauffeurRepository.save(chauffeur);
	}

	@Override
	public Chauffeur updateChauffeur(Chauffeur chauffeur) {
		Optional<Chauffeur> chauffeurDb = this.chauffeurRepository.findById(chauffeur.getId());
		
		if(chauffeurDb.isPresent()) {
			Chauffeur chauffeurUpdate = chauffeurDb.get();
			chauffeurUpdate.setId(chauffeur.getId());
			chauffeurUpdate.setName(chauffeur.getName());
			chauffeurRepository.save(chauffeurUpdate);
			return chauffeurUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + chauffeur.getId());
		}		
	}

	@Override
	public List<Chauffeur> getAllChauffeur() {
		return this.chauffeurRepository.findAll();
	}

	@Override
	public Chauffeur getChauffeurById(long chauffeurId) {
		
		Optional<Chauffeur> chauffeurDb = this.chauffeurRepository.findById(chauffeurId);
		
		if(chauffeurDb.isPresent()) {
			return chauffeurDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + chauffeurId);
		}
	}

	@Override
	public void deleteChauffeur(long chauffeurId) {
		Optional<Chauffeur> chauffeurDb = this.chauffeurRepository.findById(chauffeurId);
		
		if(chauffeurDb.isPresent()) {
			this.chauffeurRepository.delete(chauffeurDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + chauffeurId);
		}
		
	}

}
