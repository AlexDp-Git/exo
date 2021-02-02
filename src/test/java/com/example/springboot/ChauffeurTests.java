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

import com.example.springboot.model.Chauffeur;
import com.example.springboot.repository.ChauffeurRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ChauffeurTests {

	@Autowired
	private ChauffeurRepository chauffeurRepository;
	
	@Test
	public void testCreateChauffeur() {
		Chauffeur chauffeur = new Chauffeur("Chauffeur_nom", "Chauffeur_prenom");
		Chauffeur savedChauffeur = chauffeurRepository.save(chauffeur);
		assertNotNull(savedChauffeur);
	}
	
	@Test
	public void testFindChauffeurById() {
		long id = 1; 
		Optional<Chauffeur> chauffeur = chauffeurRepository.findById(id);
		
		assertNotNull(chauffeur.get());
	}
	
	@Test
	public void testUpdateChauffeur() {
		String newChauffeurName = "Chauffeur_new";
		String newChauffeurLastName = "Chauffeur_new2";
		long id = 1; 
		Chauffeur chauffeur = new Chauffeur(1, newChauffeurName,newChauffeurLastName);

		chauffeurRepository.save(chauffeur);
		Optional<Chauffeur> updatedChauffeur = chauffeurRepository.findById(id);
		assertEquals(updatedChauffeur.get().getName(), newChauffeurName);
		assertEquals(updatedChauffeur.get().getLastname(), newChauffeurLastName);
	}
	
	@Test
	public void testListChauffeur() {
		List<Chauffeur> chauffeurs = chauffeurRepository.findAll();
		
		assertFalse(chauffeurs.isEmpty());
	}
	
	@Test
	public void testDeleteChauffeur() {
		long id = 1;
		Optional<Chauffeur> chauffeur = chauffeurRepository.findById(id);
		chauffeurRepository.delete(chauffeur.get());
		
		boolean deletedChauffeur = chauffeurRepository.findById(id).isPresent();
		assertFalse(deletedChauffeur);
	}
}
