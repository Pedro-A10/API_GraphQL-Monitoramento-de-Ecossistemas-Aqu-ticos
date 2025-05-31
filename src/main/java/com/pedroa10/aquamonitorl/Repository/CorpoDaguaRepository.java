package com.pedroa10.aquamonitorl.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CorpoDagua extends JpaRepository<CorpoDagua, Long> {

	List<CorpoDagua> findByTipo(String tipo);
	
	@Query("SELECT c FROM CorpoDagua c WHERE c.localizacao.latitude BETWEEN :minLat AND :maxLat AND c.localizacao.longitude BETWEEN :minLon AND :maxLon")
	List<CorpoDagua> findByLocalizacaoRange(
		@Param("minLat") double minLat,
		@Param("maxLat") double maxLat,
		@Param("minLon") double minLon,
		@Param("maxLon") double maxLon
			);
}
