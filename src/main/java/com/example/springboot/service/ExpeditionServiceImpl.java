package com.example.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Camion;
import com.example.springboot.model.Expedition;
import com.example.springboot.model.Remorque;
import com.example.springboot.model.Tracteur;
import com.example.springboot.repository.ExpeditionRepository;
import com.example.springboot.repository.RemorqueRepository;
import com.example.springboot.repository.TracteurRepository;
@Service
@Transactional
public class ExpeditionServiceImpl implements ExpeditionService{

	
	@Autowired
	private  ExpeditionRepository expeditionRepository;
	
	@Autowired
	private  TracteurRepository tracteurRepository;
	
	
	@Autowired
	private  RemorqueRepository remorqueRepository;
	
	
	@Override
	public Expedition createExpedition(Expedition product) {
		return expeditionRepository.save(product);
	}

	@Override
	public Expedition updateExpedition(Expedition product) {
		Optional<Expedition> productDb = this.expeditionRepository.findByIdTracteurIdAndIdRemorqueId(product.getId().getTracteur().getId(), product.getId().getRemorque().getId());
		
		if(productDb.isPresent()) {
			Expedition productUpdate = productDb.get();
			productUpdate.setId(product.getId());
			productUpdate.setChargement(product.getChargement());
			productUpdate.setChauffeur(product.getChauffeur());
			expeditionRepository.save(productUpdate);
			return productUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + product.getId());
		}		
	}

	@Override
	public List<Expedition> getAllExpedition() {
		return this.expeditionRepository.findAll();
	}


	@Override
	public Expedition getExpeditionByTracteurIdAndRemorqueId(long tracteurId, long remorqueId) {
		Optional<Expedition> productDb = this.expeditionRepository.findByIdTracteurIdAndIdRemorqueId(tracteurId, remorqueId);
		if(productDb.isPresent()) {
			return productDb.get();
		} else {
		throw new ResourceNotFoundException("Record not found with id : " + tracteurId + " and " + remorqueId);
		}
	}

	@Override
	public void deleteExpeditionById(long tracteurId, long remorqueId) {
		Optional<Expedition> productDb = this.expeditionRepository.findByIdTracteurIdAndIdRemorqueId(tracteurId, remorqueId);
		if(productDb.isPresent()) {
			this.expeditionRepository.deleteByIdTracteurAndRemorque(tracteurId, remorqueId);
		} else {
		throw new ResourceNotFoundException("Record not found with id : " + tracteurId + " and " + remorqueId);
		}
	}

	@Override
	public List<Camion> getCamionFromExpedition() {
		List<Object[]> obj = this.expeditionRepository.getCamionFromExpeditions();
		List<Camion> listCamions = new ArrayList<Camion>();
		for (Object result[] : obj) {
			Tracteur t = this.tracteurRepository.findById(Long.valueOf(result[0].toString()).longValue()).get();
			Remorque r = this.remorqueRepository.findById(Long.valueOf(result[1].toString()).longValue()).get();
			Camion camion = new Camion();
			camion.setRemorque(r);
			camion.setTracteur(t);
			listCamions.add(camion);
		}
		return listCamions;
		
	}



}
