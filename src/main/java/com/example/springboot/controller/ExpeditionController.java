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

import com.example.springboot.model.Camion;
import com.example.springboot.model.Expedition;
import com.example.springboot.service.ExpeditionService;

@RestController
public class ExpeditionController {

	@Autowired
	private ExpeditionService expeditionService;
	
	/**
	 * Get all Expedition
	 * @return
	 */
	@GetMapping("/expeditions")
	public ResponseEntity<List<Expedition>> getAllExpedition(){
		return ResponseEntity.ok().body(expeditionService.getAllExpedition());
	}
	/**
	 * Get Expedition by TracteurId and RemorqueId
	 * @param tracteurid
	 * @param remorqueid
	 * @return
	 */
	@GetMapping("/expeditions/{tracteurid}/{remorqueid}")
	public ResponseEntity<Expedition> getExpeditionById(@PathVariable long tracteurid, @PathVariable long remorqueid){
		return ResponseEntity.ok().body(expeditionService.getExpeditionByTracteurIdAndRemorqueId(tracteurid, remorqueid));
	}
	/**
	 * Create Expedition
	 * @param expedition
	 * @return
	 */
	@PostMapping("/expeditions")
	public ResponseEntity<Expedition> createExpedition(@RequestBody Expedition expedition){
		return ResponseEntity.ok().body(this.expeditionService.createExpedition(expedition));
	}
	/**
	 * Update Expedition
	 * @param expedition
	 * @return
	 */
	@PutMapping("/expeditions/")
	public ResponseEntity<Expedition> updateExpedition(@RequestBody Expedition expedition){
		return ResponseEntity.ok().body(this.expeditionService.updateExpedition(expedition));
	}

	/**
	 * Delete Expedition
	 * @param id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/expeditions/{id}/{idr}")
	public HttpStatus deleteExpedition(@PathVariable long id, @PathVariable long idr){
		this.expeditionService.deleteExpeditionById(id, idr);
		return HttpStatus.OK;
	}
	
	/**
	 * Get all camion that are in a Expedition
	 * @return
	 */
	@GetMapping("/expeditions/camions")
	public ResponseEntity<List<Camion>> getAllCamion() {
		return ResponseEntity.ok().body(expeditionService.getCamionFromExpedition());
	}
}
