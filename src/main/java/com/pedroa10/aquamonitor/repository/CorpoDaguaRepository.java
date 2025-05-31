package com.pedroa10.aquamonitor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CorpoDaguaRepository extends JpaRepository<CorpoDaguaRepository, Long> {

	List<CorpoDaguaRepository> findByTipo(String tipo);
	
	@Query("SELECT c FROM CorpoDagua c WHERE c.localizacao.latitude BETWEEN :minLat AND :maxLat AND c.localizacao.longitude BETWEEN :minLon AND :maxLon")
	List<CorpoDaguaRepository> findByLocalizacaoRange(
		@Param("minLat") double minLat,
		@Param("maxLat") double maxLat,
		@Param("minLon") double minLon,
		@Param("maxLon") double maxLon
			);
}
