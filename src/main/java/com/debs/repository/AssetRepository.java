package com.debs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.debs.model.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

}
