package com.debs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.debs.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
