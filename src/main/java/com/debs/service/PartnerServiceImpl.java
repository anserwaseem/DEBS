package com.debs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debs.model.Partner;
import com.debs.repository.PartnerRepository;

@Service
public class PartnerServiceImpl implements PartnerService {
	@Autowired
	private PartnerRepository partnerRepository;

	@Override
	public List<Partner> getAllPartners() {
		return partnerRepository.findAll();
	}

	@Override
	public void savePartner(Partner partner) {
		this.partnerRepository.save(partner);
	}

	@Override
	public Partner getPartnerbyId(int id) {
		Optional<Partner> optional = partnerRepository.findById(id);
		Partner partner = null;
		if (optional.isPresent()) {
			partner = optional.get();
		} else {
			throw new RuntimeException(" Partner not found for id :: " + id);
		}
		return partner;
	}

	@Override
	public void deletePartnerById(int id) {
		this.partnerRepository.deleteById(id);
	}
}
