package com.debs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.debs.model.Expense;
import com.debs.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public List<Expense> getAllExpenses() {
		return expenseRepository.findAll();
	}

	@Override
	public void saveExpense(Expense expense) {
		this.expenseRepository.save(expense);
	}

	@Override
	public Expense getExpensebyId(int id) {
		Optional<Expense> optional = expenseRepository.findById(id);
		Expense expense = null;
		if (optional.isPresent()) {
			expense = optional.get();
		} else {
			throw new RuntimeException(" Expense not found for id :: " + id);
		}
		return expense;
	}

	@Override
	public void deleteExpenseById(int id) {
		this.expenseRepository.deleteById(id);
	}
}
