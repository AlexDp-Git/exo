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

import com.example.springboot.model.Chargement;
import com.example.springboot.repository.ChargementRepository;
import com.example.springboot.service.ChargementServiceImpl;


public class ChargementServiceTest {

	@Mock
	private ChargementRepository chargementRepository;
	
	@InjectMocks
	private ChargementServiceImpl chargementService;
	
	@Before
	 public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testListChargements() {
		List<Chargement> list = new ArrayList<>();
		Chargement chargement1 = new Chargement(1,"Chargement_Test1");
		Chargement chargement2 = new Chargement(2,"Chargement_Test2");
		
		list.add(chargement1);
		list.add(chargement2);
		
		Mockito.when(chargementRepository.findAll()).thenReturn(list);
		
		assertEquals(chargementService.getAllChargement(), list);
		
	}
	
	@Test
	public void testCreateChargement() {
		Chargement newChargement = new Chargement(1, "Chargement_Test1");
		Chargement savedChargement = new Chargement(1, "Chargement_Test1");
		List<Chargement> list = new ArrayList<>();
		list.add(newChargement);
		
		Mockito.when(chargementRepository.save(newChargement)).thenReturn(savedChargement);
		
		Chargement chargement = chargementService.createChargement(newChargement);
		
		assertEquals(chargement.getName(), newChargement.getName());
	}
	
	@Test
	public void testUpdateChargement() {
		Chargement newChargement = new Chargement(1, "Chargement_Test1");
		Chargement savedChargement = new Chargement(1, "Chargement_Test1");
		Chargement updatedChargement = new Chargement(1, "Chargement_Test1");
		Mockito.when(chargementRepository.save(newChargement)).thenReturn(savedChargement);
		
		Mockito.when(chargementRepository.findById((long) 1)).thenReturn(Optional.of(savedChargement));
		
		chargementService.updateChargement(updatedChargement);
		
		assertEquals(chargementRepository.findById((long) 1).get().getName(), updatedChargement.getName());
		
	}
	
	@Test
	public void testFindById() {
		Chargement newChargement = new Chargement(1, "Chargement_Test1");
		Chargement savedChargement = new Chargement(1, "Chargement_Test1");
		Mockito.when(chargementRepository.save(newChargement)).thenReturn(savedChargement);
		
		Mockito.when(chargementRepository.findById((long) 1)).thenReturn(Optional.of(savedChargement));
		chargementService.getChargementById((long) 1);
		
		assertEquals(chargementRepository.findById((long) 1).get(), savedChargement);
		
		
	}
	
	@Test
	public void testDeleteById() {
		Chargement newChargement = new Chargement(1, "Chargement_Test1");
		Chargement savedChargement = new Chargement(1, "Chargement_Test1");
		Mockito.when(chargementRepository.save(newChargement)).thenReturn(savedChargement);
		
		Mockito.when(chargementRepository.findById((long) 1)).thenReturn(Optional.of(savedChargement));

		

		chargementService.deleteChargement((long) 1);
		Mockito.verify(chargementRepository, times(1)).delete(savedChargement);
		
	}
}
