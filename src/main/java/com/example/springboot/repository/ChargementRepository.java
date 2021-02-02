package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.Chargement;

public interface ChargementRepository extends JpaRepository<Chargement, Long>{

}
