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

import com.example.springboot.model.Remorque;
import com.example.springboot.service.RemorqueService;

@RestController
public class RemorqueController {

	@Autowired
	private RemorqueService remorqueService;
	
	/**
	 * Get all Remorque
	 * @return
	 */
	@GetMapping("/remorques")
	public ResponseEntity<List<Remorque>> getAllRemorque(){
		return ResponseEntity.ok().body(remorqueService.getAllRemorque());
	}
	
	/**
	 * Get remorque by Id
	 * @param id
	 * @return
	 */
	@GetMapping("/remorques/{id}")
	public ResponseEntity<Remorque> getRemorqueById(@PathVariable long id){
		return ResponseEntity.ok().body(remorqueService.getRemorqueById(id));
	}
	
	/**
	 * Create remorque
	 * @param remorque
	 * @return
	 */
	@PostMapping("/remorques")
	public ResponseEntity<Remorque> createRemorque(@RequestBody Remorque remorque){
		return ResponseEntity.ok().body(this.remorqueService.createRemorque(remorque));
	}
	
	/**
	 * Update remorque
	 * @param id
	 * @param remorque
	 * @return
	 */
	@PutMapping("/remorques/{id}")
	public ResponseEntity<Remorque> updateRemorque(@PathVariable long id, @RequestBody Remorque remorque){
		remorque.setId(id);
		return ResponseEntity.ok().body(this.remorqueService.updateRemorque(remorque));
	}

	/**
	 * Delete remorque
	 * @param id
	 * @return
	 */
	@DeleteMapping("/remorques/{id}")
	public HttpStatus deleteRemorque(@PathVariable long id){
		this.remorqueService.deleteRemorque(id);
		return HttpStatus.OK;
	}
}
