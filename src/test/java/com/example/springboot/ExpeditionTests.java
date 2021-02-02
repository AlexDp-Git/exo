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

import com.example.springboot.model.Camion;
import com.example.springboot.model.Chargement;
import com.example.springboot.model.Chauffeur;
import com.example.springboot.model.Expedition;
import com.example.springboot.model.Remorque;
import com.example.springboot.model.Tracteur;
import com.example.springboot.repository.ExpeditionRepository;
import com.example.springboot.repository.RemorqueRepository;
import com.example.springboot.repository.TracteurRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExpeditionTests {

	@Autowired
	private ExpeditionRepository expeditionRepository;
	
	@Autowired
	private RemorqueRepository remorqueRepository;
	
	@Autowired
	private TracteurRepository tracteurRepository;
	
	@Test
	public void testCreateExpedition() {


		Remorque remorque = new Remorque("TestRemorqueChargement");
		
		Remorque savedRemorque = remorqueRepository.save(remorque);
		
		Tracteur tracteur = new Tracteur();
		tracteur.setName("TestTracteur");
		tracteurRepository.save(tracteur);
		
		Tracteur savedTracteur = tracteurRepository.save(tracteur);
		
		Camion camion = new Camion();
		camion.setRemorque(savedRemorque);
		camion.setTracteur(savedTracteur);
		
		Chauffeur chauffeur = new Chauffeur("TestExpeditionChauffeur", "TestE");
		
		Chargement chargement = new Chargement("TestExpeditionChargement");
		
		
		Expedition expedition = new Expedition();
		expedition.setId(camion);
		expedition.setChargement(chargement);
		expedition.setChauffeur(chauffeur);

		
		
		Expedition savedExpedition = expeditionRepository.save(expedition);
		assertNotNull(savedExpedition);
	}
	
	@Test
	public void testFindExpeditionById() {
		long tracteurId = 2;
		long remorqueId = 3;
		Optional<Expedition> expedition = expeditionRepository.findByIdTracteurIdAndIdRemorqueId(tracteurId, remorqueId);
		
		assertNotNull(expedition.get());
	}
	
	@Test
	public void testUpdateExpedition() {
		long tracteurId = 2;
		long remorqueId = 3;
		String name = "TestExpeditionNewChauffeur";
		Optional<Expedition> expedition = expeditionRepository.findByIdTracteurIdAndIdRemorqueId(tracteurId, remorqueId);
		Chauffeur newChauffeur = new Chauffeur(expedition.get().getChauffeur().getId(), "TestExpeditionNewChauffeur", expedition.get().getChauffeur().getLastname());
		
		
		Chargement newChargement = new Chargement(expedition.get().getChargement().getId(),"TestExpeditionNewChargement");

		expedition.get().setChargement(newChargement);
		expedition.get().setChauffeur(newChauffeur);
		
		
		Expedition savedExpedition = expeditionRepository.save(expedition.get());
		
		
		assertEquals(savedExpedition.getChauffeur().getName(), name);
		assertEquals(savedExpedition.getId().getRemorque(), expedition.get().getId().getRemorque());
		assertEquals(savedExpedition.getId().getTracteur(), expedition.get().getId().getTracteur());
		
	}
	
	@Test
	public void testListExpedition() {
		List<Expedition> expeditions = expeditionRepository.findAll();
		
		assertFalse(expeditions.isEmpty());
	}
	
	@Test
	public void testDeleteExpedition() {
		long tracteurId = 1;
		long remorqueId = 1;
		
		expeditionRepository.deleteByIdTracteurAndRemorque(tracteurId, remorqueId);
		
		boolean deletedExpedition = expeditionRepository.findByIdTracteurIdAndIdRemorqueId(tracteurId, remorqueId).isPresent();
		assertFalse(deletedExpedition);
	}
	
}
