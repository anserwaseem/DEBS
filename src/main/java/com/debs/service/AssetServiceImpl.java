package com.debs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debs.model.Asset;
import com.debs.repository.AssetRepository;

@Service
public class AssetServiceImpl implements AssetService {
	@Autowired
	private AssetRepository assetRepository;

	@Override
	public List<Asset> getAllAssets() {
		return assetRepository.findAll();
	}

	@Override
	public void saveAsset(Asset asset) {
		this.assetRepository.save(asset);
	}

	@Override
	public Asset getAssetbyId(long id) {
		Optional<Asset> optional = assetRepository.findById(id);
		Asset asset = null;
		if (optional.isPresent()) {
			asset = optional.get();
		} else {
			throw new RuntimeException(" Asset not found for id :: " + id);
		}
		return asset;
	}

	@Override
	public void deleteAssetById(long id) {
		this.assetRepository.deleteById(id);
	}
}
