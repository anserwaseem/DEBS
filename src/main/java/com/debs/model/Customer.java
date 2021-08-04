package com.debs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer", schema = "debs1")
public class Customer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

//	@Column(name="name") column name is set by default to attribute name
	private String name;
	private String email;
	private String phone;
	private String address;
	private String website;
	private String officeName;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	@Override
	public String toString() {
		return "id: " + id + " name: " + name + " officeName: " + officeName + " address: " + address + " website: "
				+ website + " email: " + email + " phone: " + phone;
	}

	@Override
	public boolean equals(Object obj) {
		// If the object is compared with itself then return true
		if (obj == this) {
			return true;
		}

		/*
		 * Check if obj is an instance of Customer or not "null instanceof [type]" also
		 * returns false
		 */
		if (!(obj instanceof Customer)) {
			return false;
		}

		// typecast obj to Customer so that we can compare data members
		Customer customer = (Customer) obj; // customer is callee here

		// if caller's (this) required atttributes are null, and callee's attributes are
		// not: return false
//		if ((this.name == null) && (customer.getName() != null))
//			return false;
//		else if ((this.phone == null) && (customer.getPhone() != null))
//			return false;
////		System.out.println("this.address.isEmpty(): " + this.address.isEmpty());
//		System.out.println("this.address == null: " + this.address == null);
////		System.out.println("customer.getAddress().isEmpty(): " + customer.getAddress().isEmpty());
//		System.out.println("customer.getAddress() != null: " + customer.getAddress() != null);
//		if ((this.address == null) && (customer.getAddress() != null))
//			return false;
//		else if ((this.officeName == null) && (customer.getOfficeName() != null))
//			return false;

		// if caller's required attributes equal to callee's required attributes
		if (this.name.equals(customer.getName()) && this.phone.equals(customer.getPhone())
				&& this.address.equals(customer.getAddress()) && this.officeName.equals(customer.getOfficeName())) {

			// if callee's non-required attributes are not filled: return false
			if (customer.getWebsite() != null || customer.getEmail() != null)
				return false;
			return true; // && this.email.equals(customer.getEmail()) &&
							// this.website.equals(customer.getWebsite())
		}
		return false;
	}
}
