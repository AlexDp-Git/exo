package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.Remorque;

public interface RemorqueRepository extends JpaRepository<Remorque, Long> {

}
