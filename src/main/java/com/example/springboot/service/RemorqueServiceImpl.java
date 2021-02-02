package com.example.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Remorque;
import com.example.springboot.repository.RemorqueRepository;

@Service
@Transactional
public class RemorqueServiceImpl implements RemorqueService{

	
	@Autowired
	private RemorqueRepository remorqueRepository;
	
	
	@Override
	public Remorque createRemorque(Remorque product) {
		return remorqueRepository.save(product);
	}

	@Override
	public Remorque updateRemorque(Remorque product) {
		Optional<Remorque> productDb = this.remorqueRepository.findById(product.getId());
		
		if(productDb.isPresent()) {
			Remorque productUpdate = productDb.get();
			productUpdate.setId(product.getId());
			productUpdate.setName(product.getName());
			remorqueRepository.save(productUpdate);
			return productUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + product.getId());
		}		
	}

	@Override
	public List<Remorque> getAllRemorque() {
		return this.remorqueRepository.findAll();
	}

	@Override
	public Remorque getRemorqueById(long productId) {
		
		Optional<Remorque> productDb = this.remorqueRepository.findById(productId);
		
		if(productDb.isPresent()) {
			return productDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
	}

	@Override
	public void deleteRemorque(long productId) {
		Optional<Remorque> productDb = this.remorqueRepository.findById(productId);
		
		if(productDb.isPresent()) {
			this.remorqueRepository.delete(productDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
		
	}

}
