package com.debs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.debs.model.Liability;

@Repository
public interface LiabilityRepository extends JpaRepository<Liability, Integer> {

}
