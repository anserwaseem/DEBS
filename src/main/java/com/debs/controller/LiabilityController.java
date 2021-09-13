package com.debs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.debs.model.Liability;
import com.debs.service.AccountService;
import com.debs.service.LiabilityService;

@Controller
public class LiabilityController {
	@Autowired
	private LiabilityService liabilityService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserDetails userDetails; // adding session scoped bean

	@GetMapping("/liabilities")
	public String getLiabilitiesPage(Model model) {
//		System.out.println("\n\nliabilityService.getAllliabilities().get(0):\n" + liabilityService.getAllliabilities().get(0));
		model.addAttribute("listLiabilities", liabilityService.getAllLiabilities());
		return "liabilities";
	}

	@GetMapping("/addLiability")
	public String getAddLiabilityPage(Model model) {
		Liability liability = new Liability();
		// addLiability page will access this empty liability object for binding form
		// data
		model.addAttribute("liability", liability);
		return "addLiability";
	}

	@GetMapping("/getUpdateLiabilityPage/{id}") // id is the path variable that we binded in liabilities.html inside
												// form
	public String getUpdateLiabilityPage(@PathVariable(value = "id") int id, Model model) {
		// add data to the model, then pass this model to the template (html file)

		// get liability from the service, and fill it in updateLiability.html
		Liability liability = liabilityService.getLiabilitybyId(id);

		// set liability as a model attribute to pre-populate the form
		model.addAttribute("liability", liability);
		return "updateLiability";
	}

	@PostMapping("/updateLiability")
	public String updateLiability(@ModelAttribute("liability") Liability liability, Model model) {
		// all the form data will be binded to liability (given parameter) object.
		// now, we will save liability to database
		Liability liability1 = liabilityService.getLiabilitybyId(liability.getId());
		if (!liability1.equals(liability))
			liabilityService.saveLiability(liability);
		return "redirect:/liabilities";
	}

	@PostMapping("/saveLiability")
	public String saveLiability(@ModelAttribute("liability") Liability liability, Model model) {
		// all the form data will be binded to liability (given parameter) object.
		// now, we will save liability to database
		int accountId = accountService.addAccount(userDetails.getUserId(), "liability");
		if (accountId != -1) {
			liability.setAccount_id(accountId);
			liabilityService.saveLiability(liability);
		} else {
			System.out.println(
					"Liability not added because accountId is not created correctly. Check for the right userId (maybe trying to access the page(s) without login/signup)");
		}
		return "redirect:/liabilities";
	}

	@GetMapping("/deleteLiability/{id}")
	public String deleteLiability(@PathVariable(value = "id") int id, Model model) {
		// call delete liability method
		this.liabilityService.deleteLiabilityById(id);

		model.addAttribute("listliabilities", liabilityService.getAllLiabilities());
		return "redirect:/liabilities";
	}
}
