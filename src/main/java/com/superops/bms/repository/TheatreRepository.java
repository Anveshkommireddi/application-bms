package com.superops.bms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.superops.bms.entity.TheatreEntity;

@Repository
public interface TheatreRepository extends JpaRepository<TheatreEntity, Long> {

	Optional<TheatreEntity> findByTheatreNameAndScreenName(String theatreName, String screenName);

	Optional<List<TheatreEntity>> findByLocationName(String location);

}
