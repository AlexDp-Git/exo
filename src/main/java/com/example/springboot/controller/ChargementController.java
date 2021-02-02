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

import com.example.springboot.model.Chargement;
import com.example.springboot.service.ChargementService;

@RestController
public class ChargementController {

	@Autowired
	private ChargementService chargementService;
	
	/**
	 * Get All Chargements
	 * @return
	 */
	@GetMapping("/chargements")
	public ResponseEntity<List<Chargement>> getAllChargement(){
		return ResponseEntity.ok().body(chargementService.getAllChargement());
	}
	/**
	 * Get Chargement by Id 
	 * @param id
	 * @return
	 */
	@GetMapping("/chargements/{id}")
	public ResponseEntity<Chargement> getChargementById(@PathVariable long id){
		return ResponseEntity.ok().body(chargementService.getChargementById(id));
	}
	
	/**
	 * Create Chargemennt
	 * @param chargement
	 * @return
	 */
	@PostMapping("/chargements")
	public ResponseEntity<Chargement> createChargement(@RequestBody Chargement chargement){
		return ResponseEntity.ok().body(this.chargementService.createChargement(chargement));
	}
	/**
	 * Update Chargement
	 * @param id
	 * @param chargement
	 * @return
	 */
	@PutMapping("/chargements/{id}")
	public ResponseEntity<Chargement> updateChargement(@PathVariable long id, @RequestBody Chargement chargement){
		chargement.setId(id);
		return ResponseEntity.ok().body(this.chargementService.updateChargement(chargement));
	}
	/**
	 * Delete Chargement
	 * @param id
	 * @return
	 */
	@DeleteMapping("/chargements/{id}")
	public HttpStatus deleteChargement(@PathVariable long id){
		this.chargementService.deleteChargement(id);
		return HttpStatus.OK;
	}
}
