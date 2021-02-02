package com.example.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.springboot.model.Camion;
import com.example.springboot.model.Chargement;
import com.example.springboot.model.Chauffeur;
import com.example.springboot.model.Expedition;
import com.example.springboot.model.Remorque;
import com.example.springboot.model.Tracteur;
import com.example.springboot.repository.ChargementRepository;
import com.example.springboot.repository.ChauffeurRepository;
import com.example.springboot.repository.ExpeditionRepository;
import com.example.springboot.repository.RemorqueRepository;
import com.example.springboot.repository.TracteurRepository;
import com.example.springboot.service.ExpeditionServiceImpl;


public class ExpeditionServiceTest {

	@Mock
	private ExpeditionRepository expeditionRepository;
	
	@Mock
	private ChauffeurRepository chauffeurRepository;
	
	@Mock
	private ChargementRepository chargementRepository;
	
	@Mock
	private TracteurRepository tracteurRepository;
	
	@Mock
	private RemorqueRepository remorqueRepository;
	
	@InjectMocks
	private ExpeditionServiceImpl expeditionService;
	
	private List<Expedition> list;
	
	private Chauffeur chauffeur;
	
	private Chargement chargement;
	
	private Tracteur tracteur;
	
	private Remorque remorque;
	
	private Camion camion;
	
	private Expedition expedition;
	@Before
	 public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		list = new ArrayList<>();
		chauffeur = new Chauffeur(1, "test", "testest");
		chauffeurRepository.save(chauffeur);
		   
		chargement= new Chargement("test");
		chargementRepository.save(chargement);
		   
		tracteur = new Tracteur(1,"Test");

		tracteurRepository.save(tracteur);
		remorque = new Remorque(1,"test");
		remorqueRepository.save(remorque);
		   
		camion = new Camion();
		camion.setRemorque(remorque);
		camion.setTracteur(tracteur);
		   
		expedition = new Expedition();
		expedition.setId(camion);
		expedition.setChargement(chargement);
	    expedition.setChauffeur(chauffeur);
		   
		expeditionRepository.save(expedition);
		   
		list.add(expedition);
		   
	}
	
	@Test
	public void testListExpeditions() {
		Mockito.when(expeditionRepository.findAll()).thenReturn(list);
		
		assertEquals(expeditionService.getAllExpedition(), list);
		
	}
	
	@Test
	public void testCreateExpedition() {
		Expedition newExpedition = expedition;
		Expedition savedExpedition = expedition;
		List<Expedition> list = new ArrayList<>();
		list.add(newExpedition);
		
		Mockito.when(expeditionRepository.save(newExpedition)).thenReturn(savedExpedition);
		
		Expedition expedition = expeditionService.createExpedition(newExpedition);
		
		assertEquals(expedition.getId(), newExpedition.getId());
	}
	
	@Test
	public void testUpdateExpedition() {
		Expedition newExpedition = expedition;
		Expedition savedExpedition = expedition;
		
		Expedition updatedExpedition = expedition;
		updatedExpedition.setChargement(new Chargement("newChargement"));
		Mockito.when(expeditionRepository.save(newExpedition)).thenReturn(savedExpedition);
		
		Mockito.when(expeditionRepository.findByIdTracteurIdAndIdRemorqueId(savedExpedition.getId().getTracteur().getId(), savedExpedition.getId().getRemorque().getId())).thenReturn(Optional.of(savedExpedition));
		
		expeditionService.updateExpedition(updatedExpedition);
		
		assertEquals(expeditionRepository.findByIdTracteurIdAndIdRemorqueId(savedExpedition.getId().getTracteur().getId(), savedExpedition.getId().getRemorque().getId()).get().getChargement(), updatedExpedition.getChargement());
		
	}
	
	@Test
	public void testFindById() {
		Expedition newExpedition = expedition;
		Expedition savedExpedition = expedition;
		Mockito.when(expeditionRepository.save(newExpedition)).thenReturn(savedExpedition);
		
		Mockito.when(expeditionRepository.findByIdTracteurIdAndIdRemorqueId(savedExpedition.getId().getTracteur().getId(), savedExpedition.getId().getRemorque().getId())).thenReturn(Optional.of(savedExpedition));
		expeditionService.getExpeditionByTracteurIdAndRemorqueId(savedExpedition.getId().getTracteur().getId(), savedExpedition.getId().getRemorque().getId());
		
		assertEquals(expeditionRepository.findByIdTracteurIdAndIdRemorqueId(savedExpedition.getId().getTracteur().getId(), savedExpedition.getId().getRemorque().getId()).get(), savedExpedition);
		
		
	}
	
	@Test
	public void testDeleteById() {
		Expedition newExpedition = expedition;
		Expedition savedExpedition = expedition;
		Mockito.when(expeditionRepository.save(newExpedition)).thenReturn(savedExpedition);
		
		Mockito.when(expeditionRepository.findByIdTracteurIdAndIdRemorqueId(savedExpedition.getId().getTracteur().getId(), savedExpedition.getId().getRemorque().getId())).thenReturn(Optional.of(savedExpedition));
		expeditionService.getExpeditionByTracteurIdAndRemorqueId(savedExpedition.getId().getTracteur().getId(), savedExpedition.getId().getRemorque().getId());

	

		expeditionService.deleteExpeditionById(savedExpedition.getId().getTracteur().getId(), savedExpedition.getId().getRemorque().getId());

		
	}
	
}
