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

import com.example.springboot.model.Remorque;
import com.example.springboot.repository.RemorqueRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RemorqueTests {

	@Autowired
	private RemorqueRepository chargementRepository;
	
	@Test
	public void testCreateRemorque() {
		Remorque chargement = new Remorque("Remorque_Test");
		Remorque savedRemorque = chargementRepository.save(chargement);
		assertNotNull(savedRemorque);
	}
	
	@Test
	public void testFindRemorqueById() {
		long id = 4; 
		Optional<Remorque> chargement = chargementRepository.findById(id);
		
		assertNotNull(chargement.get());
	}
	
	@Test
	public void testUpdateRemorque() {
		String newRemorqueName = "Borris";
		long id = 4; 
		Remorque chargement = new Remorque(4, newRemorqueName);
		
		chargementRepository.save(chargement);
		Optional<Remorque> updatedRemorque = chargementRepository.findById(id);
		assertEquals(updatedRemorque.get().getName(), newRemorqueName);
	}
	
	@Test
	public void testListRemorque() {
		List<Remorque> remorque = chargementRepository.findAll();
		
		assertFalse(remorque.isEmpty());
	}
	
	@Test
	public void testDeleteRemorque() {
		long id =4;
		Optional<Remorque> chargement = chargementRepository.findById(id);
		chargementRepository.delete(chargement.get());
		
		boolean deletedRemorque = chargementRepository.findById(id).isPresent();
		assertFalse(deletedRemorque);
	}
}
