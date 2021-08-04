package com.debs.service;

import java.util.List;

import com.debs.model.Expense;

public interface ExpenseService {
	List<Expense> getAllExpenses();

	void saveExpense(Expense expense);

	Expense getExpensebyId(long id);

	void deleteExpenseById(long id);
}
