package com.example.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.Chauffeur;
import com.example.springboot.service.ChauffeurService;

@RestController
public class ChauffeurController {

	@Autowired
	private ChauffeurService chauffeurService;
	
	/**
	 * Get All Chauffeurs
	 * @return
	 */
	@GetMapping("/chauffeurs")
	public ResponseEntity<List<Chauffeur>> getAllChauffeur(){
		return ResponseEntity.ok().body(chauffeurService.getAllChauffeur());
	}
	
	/**
	 * Get Chauffeur by Id
	 * @param id
	 * @return
	 */
	@GetMapping("/chauffeurs/{id}")
	public ResponseEntity<Chauffeur> getChauffeurById(@PathVariable long id){
		return ResponseEntity.ok().body(chauffeurService.getChauffeurById(id));
	}
	
	/**
	 * Create chauffeur
	 * @param chauffeur
	 * @return
	 */
	@PostMapping("/chauffeurs")
	public ResponseEntity<Chauffeur> createChauffeur(@RequestBody Chauffeur chauffeur){
		return ResponseEntity.ok().body(this.chauffeurService.createChauffeur(chauffeur));
	}
	
	/**
	 * Update chauffeur
	 * @param id
	 * @param chauffeur
	 * @return
	 */
	@PutMapping("/chauffeurs/{id}")
	public ResponseEntity<Chauffeur> updateChauffeur(@PathVariable long id, @RequestBody Chauffeur chauffeur){
		chauffeur.setId(id);
		return ResponseEntity.ok().body(this.chauffeurService.updateChauffeur(chauffeur));
	}

	/**
	 * Delete chauffeur 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/chauffeurs/{id}")
	public HttpStatus deleteChauffeur(@PathVariable long id){
		this.chauffeurService.deleteChauffeur(id);
		return HttpStatus.OK;
	}
}
