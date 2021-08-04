package com.debs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trialBalance", schema = "debs1")
public class TrialBalance {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String acc_name;
	public int debit;
	public int credit;

	public TrialBalance() {
	}

	public TrialBalance(int id, String acc_name, int debit, int credit) {
		this.id = id;
		this.acc_name = acc_name;
		this.debit = debit;
		this.credit = credit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getacc_name() {
		return acc_name;
	}

	public void setacc_name(String acc_name) {
		this.acc_name = acc_name;
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

	@Override
	public String toString() {
		return "TrialBalance [id=" + id + ", acc_name=" + acc_name + ", debit=" + debit + ", credit=" + credit + "]";
	}
}
