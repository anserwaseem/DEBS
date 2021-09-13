package com.debs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.debs.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

}
