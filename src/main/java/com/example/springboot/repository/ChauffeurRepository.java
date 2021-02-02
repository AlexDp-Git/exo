package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.Chauffeur;

public interface ChauffeurRepository extends JpaRepository<Chauffeur, Long> {

}
