package com.higor.timeline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.higor.timeline.domain.CustomData;

@Repository
public interface CustomDataRepository extends JpaRepository<CustomData, Integer>{

}
