package com.debs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.debs.model.Customer;
import com.debs.service.AccountService;
import com.debs.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired // Autowiring feature of spring framework enables you to inject the object
				// dependency implicitly. It internally uses setter or constructor injection.
				// Autowiring can't be used to inject primitive and string values.
	private CustomerService customerService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserDetails userDetails; // adding session scoped bean

	@GetMapping("/customers")
	public String getCustomersPage(Model model) {
		model.addAttribute("listCustomers", customerService.getAllCustomers());
		return "customers";
	}

	@GetMapping("/addCustomer")
	public String getAddCustomerPage(Model model) {
		Customer customer = new Customer();
		// thymeleaf template will access this empty customer object for binding form
		// data
		model.addAttribute("customer", customer);
		return "addCustomer";
	}

	@GetMapping("/getUpdateCustomerPage/{id}") // id is the path variable that we binded in customers.html inside form
	public String getUpdateCustomerPage(@PathVariable(value = "id") int id, Model model) {
		// add data to the model, then pass this model to the template (html file)

		// get customer from the service, and fill it in updateCustomer.html
		Customer customer = customerService.getCustomerbyId(id);

		// set customer as a model attribute to pre-populate the form
		model.addAttribute("customer", customer);
		return "updateCustomer";
	}

	@PostMapping("/updateCustomer")
	public String updateCustomer(@ModelAttribute("customer") Customer customer, Model model) {
		// all the form data will be binded to customer (given parameter) object.
		// now, we will save customer to database
//		if(customer.)
		Customer customer1 = customerService.getCustomerbyId(customer.getId());
//		System.out.println("\nCustomer1= " + customer1 + "\n\ncustomer= " + customer);
		if (!customer1.equals(customer))
			customerService.saveCustomer(customer);

//		model.addAttribute("listCustomers", customerService.getAllCustomers());
		return "redirect:/customers";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer, Model model) {
		// all the form data will be binded to customer (given parameter) object.
		// now, we will save customer to database
		int accountId = accountService.addAccount(userDetails.getUserId(), "customer");
		if (accountId != -1) {
			customer.setAccount_id(accountId);
			customerService.saveCustomer(customer);
		} else {
			System.out.println(
					"Customer not added because accountId is not created correctly. Check for the right userId (maybe trying to access the page(s) without login/signup)");
		}
//		model.addAttribute("listCustomers", customerService.getAllCustomers());
		return "redirect:/customers";
	}

	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable(value = "id") int id, Model model) {
		// call delete customer method
		this.customerService.deleteCustomerById(id);

		model.addAttribute("listCustomers", customerService.getAllCustomers());
		return "redirect:/customers";
	}

	@GetMapping("/salesBill")
	public String getSalesBillPage() {
		return "salesBill";
	}

	@GetMapping("/purchaseBill")
	public String getPurchaseBillPage() {
		return "purchaseBill";
	}

//	@PostMapping("/commitTransaction")
//	public String verifyCommitTransaction(@ModelAttribute("customer") Customer customer, Model model) {
//
//	}

	// this request (showNewCustomerForm) comes from index.html page
	@GetMapping("/showNewCustomerForm")
	public String showNewCustomerForm(Model model) {
		Customer customer = new Customer();
		// thymeleaf template will access this empty customer object for binding form
		// data
		model.addAttribute("customer", customer);
		return "new_customer";
	}

//	@PostMapping("/saveCustomer")
//	public String saveCustomer(@ModelAttribute("customer") Customer customer, Model model) {
//		// all the form data will be binded to customer (given parameter) object.
//		// now, we will save customer to database
//		customerService.saveCustomer(customer);
//
//		model.addAttribute("listCustomers", customerService.getAllCustomers());
//		return "customers";
//	}

	@GetMapping("/showFormForUpdate/{id}") // id is the path variable that we binded in customers.html inside form
	public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
		// add data to the model, then pass this model to the template (html file)

		// get customer from the service, and fill it in update_customer.html
		Customer customer = customerService.getCustomerbyId(id);

		// set customer as a model attribute to pre-populate the form
		model.addAttribute("customer", customer);
		return "update_customer";
	}

}
