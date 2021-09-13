package com.debs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.debs.model.Vendor;
import com.debs.service.AccountService;
import com.debs.service.VendorService;

@Controller
public class VendorController {
	@Autowired
	private VendorService vendorService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserDetails userDetails; // adding session scoped bean

	@GetMapping("/vendors")
	public String getVendorsPage(Model model) {
//		System.out.println("\n\nvendorService.getAllVendors().get(0):\n" + vendorService.getAllVendors().get(0));
		model.addAttribute("listVendors", vendorService.getAllVendors());
		return "vendors";
	}

	@GetMapping("/addVendor")
	public String getAddVendorPage(Model model) {
		Vendor vendor = new Vendor();
		// addVendor page will access this empty vendor object for binding form data
		model.addAttribute("vendor", vendor);
		return "addVendor";
	}

	@GetMapping("/getUpdateVendorPage/{id}") // id is the path variable that we binded in vendors.html inside form
	public String getUpdateVendorPage(@PathVariable(value = "id") int id, Model model) {
		// add data to the model, then pass this model to the template (html file)

		// get vendor from the service, and fill it in updateVendor.html
		Vendor vendor = vendorService.getVendorbyId(id);

		// set vendor as a model attribute to pre-populate the form
		model.addAttribute("vendor", vendor);
		return "updateVendor";
	}

	@PostMapping("/updateVendor")
	public String updateVendor(@ModelAttribute("vendor") Vendor vendor, Model model) {
		// all the form data will be binded to vendor (given parameter) object.
		// now, we will save vendor to database
		Vendor vendor1 = vendorService.getVendorbyId(vendor.getId());
		if (!vendor1.equals(vendor))
			vendorService.saveVendor(vendor);
		return "redirect:/vendors";
	}

	@PostMapping("/saveVendor")
	public String saveVendor(@ModelAttribute("vendor") Vendor vendor, Model model) {
		// all the form data will be binded to vendor (given parameter) object.
		// now, we will save vendor to database
		int accountId = accountService.addAccount(userDetails.getUserId(), "vendor");
		if (accountId != -1) {
			vendor.setAccount_id(accountId);
			vendorService.saveVendor(vendor);
		} else {
			System.out.println(
					"Vendor not added because accountId is not created correctly. Check for the right userId (maybe trying to access the page(s) without login/signup)");
		}
		return "redirect:/vendors";
	}

	@GetMapping("/deleteVendor/{id}")
	public String deleteVendor(@PathVariable(value = "id") int id, Model model) {
		// call delete vendor method
		this.vendorService.deleteVendorById(id);

		model.addAttribute("listVendors", vendorService.getAllVendors());
		return "redirect:/vendors";
	}
}
