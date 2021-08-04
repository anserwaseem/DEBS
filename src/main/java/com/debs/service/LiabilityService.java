package com.debs.service;

import java.util.List;

import com.debs.model.Liability;

public interface LiabilityService {
	List<Liability> getAllLiabilities();

	void saveLiability(Liability liability);

	Liability getLiabilitybyId(long id);

	void deleteLiabilityById(long id);
}
