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
	public Expedition createExpedition(Expedition expedition) {
		return expeditionRepository.save(expedition);
	}

	@Override
	public Expedition updateExpedition(Expedition expedition) {
		Optional<Expedition> expeditionDb = this.expeditionRepository.findByIdTracteurIdAndIdRemorqueId(expedition.getId().getTracteur().getId(), expedition.getId().getRemorque().getId());
		
		if(expeditionDb.isPresent()) {
			Expedition expeditionUpdate = expeditionDb.get();
			expeditionUpdate.setId(expedition.getId());
			expeditionUpdate.setChargement(expedition.getChargement());
			expeditionUpdate.setChauffeur(expedition.getChauffeur());
			expeditionRepository.save(expeditionUpdate);
			return expeditionUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + expedition.getId());
		}		
	}

	@Override
	public List<Expedition> getAllExpedition() {
		return this.expeditionRepository.findAll();
	}


	@Override
	public Expedition getExpeditionByTracteurIdAndRemorqueId(long tracteurId, long remorqueId) {
		Optional<Expedition> expeditionDb = this.expeditionRepository.findByIdTracteurIdAndIdRemorqueId(tracteurId, remorqueId);
		if(expeditionDb.isPresent()) {
			return expeditionDb.get();
		} else {
		throw new ResourceNotFoundException("Record not found with id : " + tracteurId + " and " + remorqueId);
		}
	}

	@Override
	public void deleteExpeditionById(long tracteurId, long remorqueId) {
		Optional<Expedition> expeditionDb = this.expeditionRepository.findByIdTracteurIdAndIdRemorqueId(tracteurId, remorqueId);
		if(expeditionDb.isPresent()) {
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
