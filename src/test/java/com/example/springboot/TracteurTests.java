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

import com.example.springboot.model.Tracteur;
import com.example.springboot.repository.TracteurRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TracteurTests {

	@Autowired
	private TracteurRepository tracteurRepository;
	
	@Test
	public void testCreateTracteur() {
		Tracteur tracteur = new Tracteur("Tracteur_Test");
		Tracteur savedTracteur = tracteurRepository.save(tracteur);
		assertNotNull(savedTracteur);
	}
	
	@Test
	public void testFindTracteurById() {
		long id = 3; 
		Optional<Tracteur> tracteur = tracteurRepository.findById(id);
		
		assertNotNull(tracteur.get());
	}
	
	@Test
	public void testUpdateTracteur() {
		String newTracteurName = "Tracteur_new";
		long id = 3; 
		Tracteur tracteur = new Tracteur();
		tracteur.setName(newTracteurName);
		tracteur.setId(3);
		
		tracteurRepository.save(tracteur);
		Optional<Tracteur> updatedTracteur = tracteurRepository.findById(id);
		assertEquals(updatedTracteur.get().getName(), newTracteurName);
	}
	
	@Test
	public void testListTracteur() {
		List<Tracteur> tracteurs = tracteurRepository.findAll();
		
		assertFalse(tracteurs.isEmpty());
	}
	
	@Test
	public void testDeleteTracteur() {
		long id = 3;
		Optional<Tracteur> tracteur = tracteurRepository.findById(id);
		tracteurRepository.delete(tracteur.get());
		
		boolean deletedTracteur = tracteurRepository.findById(id).isPresent();
		assertFalse(deletedTracteur);
	}
}
