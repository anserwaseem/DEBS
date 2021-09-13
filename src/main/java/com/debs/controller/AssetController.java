package com.debs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.debs.model.Asset;
import com.debs.service.AccountService;
import com.debs.service.AssetService;

@Controller
public class AssetController {
	@Autowired
	private AssetService assetService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserDetails userDetails; // adding session scoped bean

	@GetMapping("/assets")
	public String getAssetsPage(Model model) {
//		System.out.println("\n\nassetService.getAllAssets().get(0):\n" + assetService.getAllAssets().get(0));
		model.addAttribute("listAssets", assetService.getAllAssets());
		return "assets";
	}

	@GetMapping("/addAsset")
	public String getAddAssetPage(Model model) {
		Asset asset = new Asset();
		// addAsset page will access this empty asset object for binding form data
		model.addAttribute("asset", asset);
		return "addAsset";
	}

	@GetMapping("/getUpdateAssetPage/{id}") // id is the path variable that we binded in assets.html inside form
	public String getUpdateAssetPage(@PathVariable(value = "id") int id, Model model) {
		// add data to the model, then pass this model to the template (html file)

		// get asset from the service, and fill it in updateAsset.html
		Asset asset = assetService.getAssetbyId(id);

		// set asset as a model attribute to pre-populate the form
		model.addAttribute("asset", asset);
		return "updateAsset";
	}

	@PostMapping("/updateAsset")
	public String updateAsset(@ModelAttribute("asset") Asset asset, Model model) {
		// all the form data will be binded to asset (given parameter) object.
		// now, we will save asset to database
		Asset asset1 = null;
		try {
			asset1 = assetService.getAssetbyId(asset.getId());
		} catch (RuntimeException re) {
			System.out.println(re);
		}

		if (!asset1.equals(asset))
			assetService.saveAsset(asset);
		return "redirect:/assets";
	}

	@PostMapping("/saveAsset")
	public String saveAsset(@ModelAttribute("asset") Asset asset, Model model) {
		// all the form data will be binded to asset (given parameter) object.
		// now, we will save asset to database
		int accountId = accountService.addAccount(userDetails.getUserId(), "asset");
		if (accountId != -1) {
			asset.setAccount_id(accountId);
			assetService.saveAsset(asset);
		} else {
			System.out.println(
					"Asset not added because accountId is not created correctly. Check for the right userId (maybe trying to access the page(s) without login/signup)");
		}
		return "redirect:/assets";
	}

	@GetMapping("/showAssetsPage")
	public String showAssetsPage(Model model) {
		model.addAttribute("listAssets", assetService.getAllAssets());
		return "/assets";
	}

	@GetMapping("/deleteAsset/{id}")
	public String deleteAsset(@PathVariable(value = "id") int id, Model model) {
		// call delete asset method
		this.assetService.deleteAssetById(id);

		model.addAttribute("listAssets", assetService.getAllAssets());
		return "redirect:/assets";
	}
}
