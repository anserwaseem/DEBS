package com.debs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debs.model.Vendor;
import com.debs.repository.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {
	@Autowired
	private VendorRepository vendorRepository;

	@Override
	public List<Vendor> getAllVendors() {
		return vendorRepository.findAll();
	}

	@Override
	public void saveVendor(Vendor vendor) {
		this.vendorRepository.save(vendor);
	}

	@Override
	public Vendor getVendorbyId(int id) {
		Optional<Vendor> optional = vendorRepository.findById(id);
		Vendor vendor = null;
		if (optional.isPresent()) {
			vendor = optional.get();
		} else {
			throw new RuntimeException(" Vendor not found for id :: " + id);
		}
		return vendor;
	}

	@Override
	public void deleteVendorById(int id) {
		this.vendorRepository.deleteById(id);
	}
}
