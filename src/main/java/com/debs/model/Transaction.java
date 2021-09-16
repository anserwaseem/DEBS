package com.debs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "transaction", schema = "debs1")
public class Transaction {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date date;
	private String account;
	private String description;
	private int debit;
	private int credit;
	@Transient
	private String accountType;

	public void setTransaction(String accountType, java.util.Date date, String account, int credit, int debit,
			String description) {
		this.accountType = accountType;
		this.date = date;
		this.account = account;
		this.description = description;
		this.debit = debit;
		this.credit = credit;
	}

	public void setTransaction(Transaction t) {
		this.accountType = t.getAccountType();
		this.date = t.getDate();
		this.account = t.getAccount();
		this.description = t.getDescription();
		this.debit = t.getDebit();
		this.credit = t.getCredit();
	}

	@Override
	public String toString() {
		return "id: " + id + " accountType: " + accountType + " account: " + account + " debit: " + debit + " credit: "
				+ credit + " description: " + description + " date: " + date.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDebit() {
		return debit;
	}

	public void setDebit(int debit) {
		this.debit = debit;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
