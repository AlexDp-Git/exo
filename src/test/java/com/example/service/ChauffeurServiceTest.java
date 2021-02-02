package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.springboot.model.Chauffeur;
import com.example.springboot.repository.ChauffeurRepository;
import com.example.springboot.service.ChauffeurServiceImpl;


public class ChauffeurServiceTest {

	@Mock
	private ChauffeurRepository chauffeurRepository;
	
	@InjectMocks
	private ChauffeurServiceImpl chauffeurService;
	
	@Before
	 public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testListChauffeurs() {
		List<Chauffeur> list = new ArrayList<>();
		Chauffeur chauffeur1 = new Chauffeur(1,"Borris", "Joe");
		Chauffeur chauffeur2 = new Chauffeur(2,"Michel", "Ri");
		
		list.add(chauffeur1);
		list.add(chauffeur2);
		
		Mockito.when(chauffeurRepository.findAll()).thenReturn(list);
		
		assertEquals(chauffeurService.getAllChauffeur(), list);
		
	}
	
	@Test
	public void testCreateChauffeur() {
		Chauffeur newChauffeur = new Chauffeur(1, "Borris", "Joe");
		Chauffeur savedChauffeur = new Chauffeur(1, "Borris", "Joe");
		List<Chauffeur> list = new ArrayList<>();
		list.add(newChauffeur);
		
		Mockito.when(chauffeurRepository.save(newChauffeur)).thenReturn(savedChauffeur);
		
		Chauffeur chauffeur = chauffeurService.createChauffeur(newChauffeur);
		
		assertEquals(chauffeur.getName(), newChauffeur.getName());
		assertEquals(chauffeur.getLastname(), newChauffeur.getLastname());
	}
	
	@Test
	public void testUpdateChauffeur() {
		Chauffeur newChauffeur = new Chauffeur(1, "Borris", "Joe");
		Chauffeur savedChauffeur = new Chauffeur(1, "Borris", "Joe");
		Chauffeur updatedChauffeur = new Chauffeur(1, "Paul", "Joe");
		Mockito.when(chauffeurRepository.save(newChauffeur)).thenReturn(savedChauffeur);
		
		Mockito.when(chauffeurRepository.findById((long) 1)).thenReturn(Optional.of(savedChauffeur));
		
		chauffeurService.updateChauffeur(updatedChauffeur);
		
		assertEquals(chauffeurRepository.findById((long) 1).get().getName(), updatedChauffeur.getName());
		
	}
	
	@Test
	public void testFindById() {
		Chauffeur newChauffeur = new Chauffeur(1, "Borris", "Joe");
		Chauffeur savedChauffeur = new Chauffeur(1, "Borris", "Joe");
		Mockito.when(chauffeurRepository.save(newChauffeur)).thenReturn(savedChauffeur);
		
		Mockito.when(chauffeurRepository.findById((long) 1)).thenReturn(Optional.of(savedChauffeur));
		chauffeurService.getChauffeurById((long) 1);
		
		assertEquals(chauffeurRepository.findById((long) 1).get(), savedChauffeur);
		
		
	}
	
	@Test
	public void testDeleteById() {
		Chauffeur newChauffeur = new Chauffeur(1, "Borris", "Joe");
		Chauffeur savedChauffeur = new Chauffeur(1, "Borris", "Joe");
		Mockito.when(chauffeurRepository.save(newChauffeur)).thenReturn(savedChauffeur);
		
		Mockito.when(chauffeurRepository.findById((long) 1)).thenReturn(Optional.of(savedChauffeur));

		

		chauffeurService.deleteChauffeur((long) 1);
		Mockito.verify(chauffeurRepository, times(1)).delete(savedChauffeur);
		
	}
}
