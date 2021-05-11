package com.superops.bms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.superops.bms.entity.APIPermsEntity;

@Repository
public interface APIPermsRepository extends JpaRepository<APIPermsEntity, String> {

	Optional<APIPermsEntity> findByUriAndAction(String uri, String httpMethod);

}
