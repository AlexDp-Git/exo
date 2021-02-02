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

import com.example.springboot.model.Tracteur;
import com.example.springboot.service.TracteurService;

@RestController
public class TracteurController {

	@Autowired
	private TracteurService tracteurService;
	
	/**
	 * Get all Tracteurs
	 * @return
	 */
	@GetMapping("/tracteurs")
	public ResponseEntity<List<Tracteur>> getAllTracteur(){
		return ResponseEntity.ok().body(tracteurService.getAllTracteur());
	}
	
	/**
	 * Get Tracteur by Id
	 * @param id
	 * @return
	 */
	@GetMapping("/tracteurs/{id}")
	public ResponseEntity<Tracteur> getTracteurById(@PathVariable long id){
		return ResponseEntity.ok().body(tracteurService.getTracteurById(id));
	}
	
	/**
	 * Create Tracteur
	 * @param tracteur
	 * @return
	 */
	@PostMapping("/tracteurs")
	public ResponseEntity<Tracteur> createTracteur(@RequestBody Tracteur tracteur){
		return ResponseEntity.ok().body(this.tracteurService.createTracteur(tracteur));
	}
	
	/**
	 * Update Tracteur
	 * @param id
	 * @param tracteur
	 * @return
	 */
	@PutMapping("/tracteurs/{id}")
	public ResponseEntity<Tracteur> updateTracteur(@PathVariable long id, @RequestBody Tracteur tracteur){
		tracteur.setId(id);
		return ResponseEntity.ok().body(this.tracteurService.updateTracteur(tracteur));
	}

	/**
	 * Delete Tracteur
	 * @param id
	 * @return
	 */
	@DeleteMapping("/tracteurs/{id}")
	public HttpStatus deleteTracteur(@PathVariable long id){
		this.tracteurService.deleteTracteur(id);
		return HttpStatus.OK;
	}
}
