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

import com.example.springboot.model.Tracteur;
import com.example.springboot.repository.TracteurRepository;
import com.example.springboot.service.TracteurServiceImpl;


public class TracteurServiceTest {

	@Mock
	private TracteurRepository tracteurRepository;
	
	@InjectMocks
	private TracteurServiceImpl tracteurService;
	
	@Before
	 public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testListTracteurs() {
		List<Tracteur> list = new ArrayList<>();
		Tracteur tracteur1 = new Tracteur(1,"Borris");
		Tracteur tracteur2 = new Tracteur(2,"Michel");
		
		list.add(tracteur1);
		list.add(tracteur2);
		
		Mockito.when(tracteurRepository.findAll()).thenReturn(list);
		
		assertEquals(tracteurService.getAllTracteur(), list);
		
	}
	
	@Test
	public void testCreateTracteur() {
		Tracteur newTracteur = new Tracteur(1, "Borris");
		Tracteur savedTracteur = new Tracteur(1, "Borris");
		List<Tracteur> list = new ArrayList<>();
		list.add(newTracteur);
		
		Mockito.when(tracteurRepository.save(newTracteur)).thenReturn(savedTracteur);
		
		Tracteur tracteur = tracteurService.createTracteur(newTracteur);
		
		assertEquals(tracteur.getName(), newTracteur.getName());
	}
	
	@Test
	public void testUpdateTracteur() {
		Tracteur newTracteur = new Tracteur(1, "Borris");
		Tracteur savedTracteur = new Tracteur(1, "Borris");
		Tracteur updatedTracteur = new Tracteur(1, "Paul");
		Mockito.when(tracteurRepository.save(newTracteur)).thenReturn(savedTracteur);
		
		Mockito.when(tracteurRepository.findById((long) 1)).thenReturn(Optional.of(savedTracteur));
		
		tracteurService.updateTracteur(updatedTracteur);
		
		assertEquals(tracteurRepository.findById((long) 1).get().getName(), updatedTracteur.getName());
		
	}
	
	@Test
	public void testFindById() {
		Tracteur newTracteur = new Tracteur(1, "Borris");
		Tracteur savedTracteur = new Tracteur(1, "Borris");
		Mockito.when(tracteurRepository.save(newTracteur)).thenReturn(savedTracteur);
		
		Mockito.when(tracteurRepository.findById((long) 1)).thenReturn(Optional.of(savedTracteur));
		tracteurService.getTracteurById((long) 1);
		
		assertEquals(tracteurRepository.findById((long) 1).get(), savedTracteur);
		
		
	}
	
	@Test
	public void testDeleteById() {
		Tracteur newTracteur = new Tracteur(1, "Borris");
		Tracteur savedTracteur = new Tracteur(1, "Borris");
		Mockito.when(tracteurRepository.save(newTracteur)).thenReturn(savedTracteur);
		
		Mockito.when(tracteurRepository.findById((long) 1)).thenReturn(Optional.of(savedTracteur));

		

		tracteurService.deleteTracteur((long) 1);
		Mockito.verify(tracteurRepository, times(1)).delete(savedTracteur);
		
	}
}
