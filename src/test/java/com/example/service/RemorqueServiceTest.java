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

import com.example.springboot.model.Remorque;
import com.example.springboot.repository.RemorqueRepository;
import com.example.springboot.service.RemorqueServiceImpl;


public class RemorqueServiceTest {

	@Mock
	private RemorqueRepository remorqueRepository;
	
	@InjectMocks
	private RemorqueServiceImpl remorqueService;
	
	@Before
	 public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testListRemorques() {
		List<Remorque> list = new ArrayList<>();
		Remorque remorque1 = new Remorque(1,"Remorque_Test1");
		Remorque remorque2 = new Remorque(2,"Remorque_Test2");
		
		list.add(remorque1);
		list.add(remorque2);
		
		Mockito.when(remorqueRepository.findAll()).thenReturn(list);
		
		assertEquals(remorqueService.getAllRemorque(), list);
		
	}
	
	@Test
	public void testCreateRemorque() {
		Remorque newRemorque = new Remorque(1, "Remorque_Test1");
		Remorque savedRemorque = new Remorque(1, "Remorque_Test1");
		List<Remorque> list = new ArrayList<>();
		list.add(newRemorque);
		
		Mockito.when(remorqueRepository.save(newRemorque)).thenReturn(savedRemorque);
		
		Remorque remorque = remorqueService.createRemorque(newRemorque);
		
		assertEquals(remorque.getName(), newRemorque.getName());
	}
	
	@Test
	public void testUpdateRemorque() {
		Remorque newRemorque = new Remorque(1, "Remorque_Test1");
		Remorque savedRemorque = new Remorque(1, "Remorque_Test1");
		Remorque updatedRemorque = new Remorque(1, "Remorque_Test2");
		Mockito.when(remorqueRepository.save(newRemorque)).thenReturn(savedRemorque);
		
		Mockito.when(remorqueRepository.findById((long) 1)).thenReturn(Optional.of(savedRemorque));
		
		remorqueService.updateRemorque(updatedRemorque);
		
		assertEquals(remorqueRepository.findById((long) 1).get().getName(), updatedRemorque.getName());
		
	}
	
	@Test
	public void testFindById() {
		Remorque newRemorque = new Remorque(1, "Remorque_Test1");
		Remorque savedRemorque = new Remorque(1, "Remorque_Test1");
		Mockito.when(remorqueRepository.save(newRemorque)).thenReturn(savedRemorque);
		
		Mockito.when(remorqueRepository.findById((long) 1)).thenReturn(Optional.of(savedRemorque));
		remorqueService.getRemorqueById((long) 1);
		
		assertEquals(remorqueRepository.findById((long) 1).get(), savedRemorque);
		
		
	}
	
	@Test
	public void testDeleteById() {
		Remorque newRemorque = new Remorque(1, "Remorque_Test1");
		Remorque savedRemorque = new Remorque(1, "Remorque_Test1");
		Mockito.when(remorqueRepository.save(newRemorque)).thenReturn(savedRemorque);
		
		Mockito.when(remorqueRepository.findById((long) 1)).thenReturn(Optional.of(savedRemorque));

		

		remorqueService.deleteRemorque((long) 1);
		Mockito.verify(remorqueRepository, times(1)).delete(savedRemorque);
		
	}
}
