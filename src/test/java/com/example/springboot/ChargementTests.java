package com.example.springboot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springboot.model.Chargement;
import com.example.springboot.repository.ChargementRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ChargementTests {

	@Autowired
	private ChargementRepository chargementRepository;
	
	@Test
	public void testCreateChargement() {
		Chargement chargement = new Chargement("Chargement_Test");

		Chargement savedChargement = chargementRepository.save(chargement);
		assertNotNull(savedChargement);
	}
	
	@Test
	public void testFindChargementById() {
		long id = 2; 
		Optional<Chargement> chargement = chargementRepository.findById(id);
		
		assertNotNull(chargement.get());
	}
	
	@Test
	public void testUpdateChargement() {
		String newChargementName = "Chargement_New";
		long id = 2; 
		Chargement chargement = new Chargement(2,newChargementName);

		
		chargementRepository.save(chargement);
		Optional<Chargement> updatedChargement = chargementRepository.findById(id);
		assertEquals(updatedChargement.get().getName(), newChargementName);
	}
	
	@Test
	public void testListChargement() {
		List<Chargement> chargements = chargementRepository.findAll();
		
		assertFalse(chargements.isEmpty());
	}
	
	@Test
	public void testDeleteChargement() {
		long id = 2;
		Optional<Chargement> chargement = chargementRepository.findById(id);
		chargementRepository.delete(chargement.get());
		
		boolean deletedChargement = chargementRepository.findById(id).isPresent();
		assertFalse(deletedChargement);
	}
}
