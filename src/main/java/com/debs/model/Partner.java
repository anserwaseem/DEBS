package com.debs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "partner", schema = "debs1")
public class Partner {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String amount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "id: " + id + " name: " + name + " amount: " + amount;
	}

	@Override
	public boolean equals(Object obj) {
		// If the object is compared with itself then return true
		if (obj == this) {
			return true;
		}

		// Check if obj is an instance of Partner or not "null instanceof [type]" also
		// returns false
		if (!(obj instanceof Partner)) {
			return false;
		}

		// typecast obj to Partner so that we can compare data members
		Partner partner = (Partner) obj; // partner is callee here

		// if caller's required attributes equal to callee's required attributes
		if (this.name.equals(partner.getName()) && this.amount.equals(partner.getAmount()))
			return true;

		return false;
	}
}
