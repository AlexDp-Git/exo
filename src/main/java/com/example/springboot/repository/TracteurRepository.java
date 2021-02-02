package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.Tracteur;

public interface TracteurRepository extends JpaRepository<Tracteur, Long> {

}
