package com.debs.service;

import java.util.List;

import com.debs.model.Partner;

public interface PartnerService {
	List<Partner> getAllPartners();

	void savePartner(Partner partner);

	Partner getPartnerbyId(long id);

	void deletePartnerById(long id);
}
