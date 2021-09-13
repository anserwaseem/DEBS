package com.debs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.debs.model.TransactionCreationDto;
import com.debs.service.AccountService;
import com.debs.service.TransactionService;

@Controller
public class TransactionController {
	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AccountService accountService;

//	@Autowired
//	private CustomerService customerService;
//
//	@Autowired
//	private VendorService vendorService;
//
//	@Autowired
//	private AssetService assetService;
//
//	@Autowired
//	private LiabilityService liabilityService;
//
//	@Autowired
//	private ExpenseService expenseService;

//	@Autowired
//	private SaleService saleService;
//
//	@Autowired
//	private PurchaseService purchaseService;
//
//	@Autowired
//	private ProductService productService;
//
//	@Autowired
//	private PartnerService partnerService;

	@Autowired
	private UserDetails userDetails; // adding session scoped bean

	@GetMapping("/listTransactions")
	public String getListTransactionsPage(Model model) {
		model.addAttribute("listOfTransactions", transactionService.getAllTransactions());
		return "listTransactions";
	}

	@GetMapping("/createTransaction")
	public String getCreateTransactionPage(Model model) {
//		model.addAttribute("transactions", accountService.getAllAccounts());
		model.addAttribute("customerAccounts", accountService.getAllAccountsNames(userDetails.getUserId(), "customer"));
		model.addAttribute("vendorAccounts", accountService.getAllAccountsNames(userDetails.getUserId(), "vendor"));
		model.addAttribute("assetAccounts", accountService.getAllAccountsNames(userDetails.getUserId(), "asset"));
		model.addAttribute("liabilityAccounts",
				accountService.getAllAccountsNames(userDetails.getUserId(), "liability"));
		model.addAttribute("expenseAccounts", accountService.getAllAccountsNames(userDetails.getUserId(), "expense"));
//		model.addAttribute("saleAccounts", saleService.getAllSalesNames());
//		model.addAttribute("purchaseAccounts", purchaseService.getAllPurchasesNames());
//		model.addAttribute("productAccounts", productService.getAllProductsNames());
		model.addAttribute("partnerAccounts", accountService.getAllAccountsNames(userDetails.getUserId(), "partner"));

		TransactionCreationDto transactionForm = new TransactionCreationDto();

		model.addAttribute("transactionForm", transactionForm);

		return "createTransaction";
	}

	@PostMapping("/commitTransaction")
	public String saveTransaction(@ModelAttribute("transactionForm") TransactionCreationDto transactionForm,
			Model model) {
		System.out.println("\n\n" + transactionForm.getRowsOfTransaction() + "\n\n");
		if (transactionService.commitTransaction(transactionForm.getRowsOfTransaction(), userDetails.getUserId()))
			return "redirect:/listTransactions";

		return "redirect:/createTransaction";
	}
}
