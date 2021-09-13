package com.debs.service;

import java.util.List;

import com.debs.model.Asset;

public interface AssetService {
	List<Asset> getAllAssets();

	void saveAsset(Asset asset);

	Asset getAssetbyId(int id);

	void deleteAssetById(int id);
}
