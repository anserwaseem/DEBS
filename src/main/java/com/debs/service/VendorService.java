package com.debs.service;

import java.util.List;

import com.debs.model.Vendor;

public interface VendorService {
	List<Vendor> getAllVendors();

	void saveVendor(Vendor vendor);

	Vendor getVendorbyId(int id);

	void deleteVendorById(int id);
}
