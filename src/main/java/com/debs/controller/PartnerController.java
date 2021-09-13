package com.debs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.debs.model.Partner;
import com.debs.service.AccountService;
import com.debs.service.PartnerService;

@Controller
public class PartnerController {
	@Autowired
	private PartnerService partnerService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserDetails userDetails; // adding session scoped bean

	@GetMapping("/capital")
	public String getCapitalPage(Model model) {
//		System.out.println("\n\npartnerService.getAllPartners().get(0):\n" + partnerService.getAllPartners().get(0));
		model.addAttribute("listPartners", partnerService.getAllPartners());
		return "capital";
	}

	@GetMapping("/addPartner")
	public String getAddPartnerPage(Model model) {
		Partner partner = new Partner();
		// addPartner page will access this empty partner object for binding form data
		model.addAttribute("partner", partner);
		return "addPartner";
	}

	@GetMapping("/getUpdatePartnerPage/{id}") // id is the path variable that we binded in partners.html inside form
	public String getUpdatePartnerPage(@PathVariable(value = "id") int id, Model model) {
		// add data to the model, then pass this model to the template (html file)

		// get partner from the service, and fill it in updatePartner.html
		Partner partner = partnerService.getPartnerbyId(id);

		// set partner as a model attribute to pre-populate the form
		model.addAttribute("partner", partner);
		return "updatePartner";
	}

	@PostMapping("/updatePartner")
	public String updatePartner(@ModelAttribute("partner") Partner partner, Model model) {
		// all the form data will be binded to partner (given parameter) object.
		// now, we will save partner to database
		Partner partner1 = partnerService.getPartnerbyId(partner.getId());
		if (!partner1.equals(partner))
			partnerService.savePartner(partner);
		return "redirect:/capital";
	}

	@PostMapping("/savePartner")
	public String savePartner(@ModelAttribute("partner") Partner partner, Model model) {
		// all the form data will be binded to partner (given parameter) object.
		// now, we will save partner to database
		int accountId = accountService.addAccount(userDetails.getUserId(), "partner");
		if (accountId != -1) {
			partner.setAccount_id(accountId);
			partnerService.savePartner(partner);
		} else {
			System.out.println(
					"Partner not added because accountId is not created correctly. Check for the right userId (maybe trying to access the page(s) without login/signup)");
		}
		return "redirect:/capital";
	}

	@GetMapping("/deletePartner/{id}")
	public String deletePartner(@PathVariable(value = "id") int id, Model model) {
		// call delete partner method
		this.partnerService.deletePartnerById(id);

		model.addAttribute("listPartners", partnerService.getAllPartners());
		return "redirect:/capital";
	}
}
