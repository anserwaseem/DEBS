package com.debs.service;

import java.util.List;

import com.debs.model.Expense;

public interface ExpenseService {
	List<Expense> getAllExpenses();

	void saveExpense(Expense expense);

	Expense getExpensebyId(int id);

	void deleteExpenseById(int id);
}
