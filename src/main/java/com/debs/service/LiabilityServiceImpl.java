package com.debs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debs.model.Liability;
import com.debs.repository.LiabilityRepository;

@Service
public class LiabilityServiceImpl implements LiabilityService {
	@Autowired
	private LiabilityRepository liabilityRepository;

	@Override
	public List<Liability> getAllLiabilities() {
		return liabilityRepository.findAll();
	}

	@Override
	public void saveLiability(Liability liability) {
		this.liabilityRepository.save(liability);
	}

	@Override
	public Liability getLiabilitybyId(long id) {
		Optional<Liability> optional = liabilityRepository.findById(id);
		Liability liability = null;
		if (optional.isPresent()) {
			liability = optional.get();
		} else {
			throw new RuntimeException(" Liability not found for id :: " + id);
		}
		return liability;
	}

	@Override
	public void deleteLiabilityById(long id) {
		this.liabilityRepository.deleteById(id);
	}
}
