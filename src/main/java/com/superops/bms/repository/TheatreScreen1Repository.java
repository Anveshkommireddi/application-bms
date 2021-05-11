package com.superops.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.superops.bms.entity.TheatreScreen1Entity;

@Repository
public interface TheatreScreen1Repository extends JpaRepository<TheatreScreen1Entity, Long> {

}
