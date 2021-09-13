package com.debs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.debs.model.Expense;
import com.debs.service.AccountService;
import com.debs.service.ExpenseService;

@Controller
public class ExpenseController {
	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserDetails userDetails; // adding session scoped bean

	@GetMapping("/expenses")
	public String getExpensesPage(Model model) {
//		System.out.println("\n\nexpenseService.getAllExpenses().get(0):\n" + expenseService.getAllExpenses().get(0));
		model.addAttribute("listExpenses", expenseService.getAllExpenses());
		return "expenses";
	}

	@GetMapping("/addExpense")
	public String getAddExpensePage(Model model) {
		Expense expense = new Expense();
		// addExpense page will access this empty expense object for binding form data
		model.addAttribute("expense", expense);
		return "addExpense";
	}

	@GetMapping("/getUpdateExpensePage/{id}") // id is the path variable that we binded in expenses.html inside form
	public String getUpdateExpensePage(@PathVariable(value = "id") int id, Model model) {
		// add data to the model, then pass this model to the template (html file)

		// get expense from the service, and fill it in updateExpense.html
		Expense expense = expenseService.getExpensebyId(id);

		// set expense as a model attribute to pre-populate the form
		model.addAttribute("expense", expense);
		return "updateExpense";
	}

	@PostMapping("/updateExpense")
	public String updateExpense(@ModelAttribute("expense") Expense expense, Model model) {
		// all the form data will be binded to expense (given parameter) object.
		// now, we will save expense to database
		Expense expense1 = expenseService.getExpensebyId(expense.getId());
		if (!expense1.equals(expense))
			expenseService.saveExpense(expense);
		return "redirect:/expenses";
	}

	@PostMapping("/saveExpense")
	public String saveExpense(@ModelAttribute("expense") Expense expense, Model model) {
		// all the form data will be binded to expense (given parameter) object.
		// now, we will save expense to database
//		expenseService.saveExpense(expense);
		int accountId = accountService.addAccount(userDetails.getUserId(), "expense");
		if (accountId != -1) {
			expense.setAccount_id(accountId);
			expenseService.saveExpense(expense);
		} else {
			System.out.println(
					"Expense not added because accountId is not created correctly. Check for the right userId (maybe trying to access the page(s) without login/signup)");
		}
		return "redirect:/expenses";
	}

	@GetMapping("/deleteExpense/{id}")
	public String deleteExpense(@PathVariable(value = "id") int id, Model model) {
		// call delete expense method
		this.expenseService.deleteExpenseById(id);

		model.addAttribute("listExpenses", expenseService.getAllExpenses());
		return "redirect:/expenses";
	}
}
