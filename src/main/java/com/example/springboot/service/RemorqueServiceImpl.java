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
	public Remorque createRemorque(Remorque remorque) {
		return remorqueRepository.save(remorque);
	}

	@Override
	public Remorque updateRemorque(Remorque remorque) {
		Optional<Remorque> remorqueDb = this.remorqueRepository.findById(remorque.getId());
		
		if(remorqueDb.isPresent()) {
			Remorque remorqueUpdate = remorqueDb.get();
			remorqueUpdate.setId(remorque.getId());
			remorqueUpdate.setName(remorque.getName());
			remorqueRepository.save(remorqueUpdate);
			return remorqueUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + remorque.getId());
		}		
	}

	@Override
	public List<Remorque> getAllRemorque() {
		return this.remorqueRepository.findAll();
	}

	@Override
	public Remorque getRemorqueById(long remorqueId) {
		
		Optional<Remorque> remorqueDb = this.remorqueRepository.findById(remorqueId);
		
		if(remorqueDb.isPresent()) {
			return remorqueDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + remorqueId);
		}
	}

	@Override
	public void deleteRemorque(long remorqueId) {
		Optional<Remorque> remorqueDb = this.remorqueRepository.findById(remorqueId);
		
		if(remorqueDb.isPresent()) {
			this.remorqueRepository.delete(remorqueDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + remorqueId);
		}
		
	}

}
