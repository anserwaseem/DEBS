package com.debs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.debs.service.TransactionService;

@Controller
public class TransactionController {
	@Autowired
	private TransactionService transactionService;

	@GetMapping("/listTransactions")
	public String getListTransactionsPage(Model model) {
		model.addAttribute("listOfTransactions", transactionService.getAllTransactions());
		return "listTransactions";
	}

	@GetMapping("/createTransaction")
	public String getCreateTransactionPage() {
		return "createTransaction";
	}
}
