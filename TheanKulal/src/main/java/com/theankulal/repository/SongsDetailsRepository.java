package com.theankulal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theankulal.entity.SongsDetails;

@Repository
public interface SongsDetailsRepository extends JpaRepository<SongsDetails, Long>{

}
