package com.example.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.springboot.model.Expedition;

public interface ExpeditionRepository extends JpaRepository<Expedition, Long>{


	public Optional<Expedition> findByIdTracteurIdAndIdRemorqueId(long tracteur, long remorque);
	
	@Modifying
	@Query(value = "DELETE FROM expeditions WHERE id_tracteur=:idTracteur AND id_remorque=:idRemorque", nativeQuery=true)
	public void deleteByIdTracteurAndRemorque(@Param("idTracteur") long tracteur, @Param("idRemorque") long remorque);
	
	
	@Query(value = "SELECT ID_TRACTEUR, ID_REMORQUE FROM EXPEDITIONS", nativeQuery=true)
	public List<Object[]> getCamionFromExpeditions();
	
	
	
}
