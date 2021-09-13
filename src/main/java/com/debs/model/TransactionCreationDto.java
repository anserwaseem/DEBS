package com.debs.model;

public class TransactionCreationDto {

	private String rowsOfTransaction;

	// default constructor
	public TransactionCreationDto() {
	}

	// parameterized constructor
	public TransactionCreationDto(String rowsOfTransaction) {
		this.rowsOfTransaction = rowsOfTransaction;
	}

	public String getRowsOfTransaction() {
		return rowsOfTransaction;
	}

	public void setRowsOfTransaction(String rowsOfTransaction) {
		this.rowsOfTransaction = rowsOfTransaction;
	}

}