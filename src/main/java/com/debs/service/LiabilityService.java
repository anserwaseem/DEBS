package com.debs.service;

import java.util.List;

import com.debs.model.Liability;

public interface LiabilityService {
	List<Liability> getAllLiabilities();

	void saveLiability(Liability liability);

	Liability getLiabilitybyId(int id);

	void deleteLiabilityById(int id);
}
