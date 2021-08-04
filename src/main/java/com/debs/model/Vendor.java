package com.debs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vendor", schema = "debs1")
public class Vendor {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "id: " + id + " name: " + name + " description: " + description + " officeName: " + officeName
				+ " address: " + address + " website: " + website + " email: " + email + " phone: " + phone;
	}

	@Override
	public boolean equals(Object obj) {
		// If the object is compared with itself then return true
		if (obj == this) {
			return true;
		}

		// Check if obj is an instance of Vendor or not "null instanceof [type]" also
		// returns false
		if (!(obj instanceof Vendor)) {
			return false;
		}

		// typecast obj to Customer so that we can compare data members
		Vendor vendor = (Vendor) obj; // vendor is callee here

		// if caller's required attributes equal to callee's required attributes
		if (this.name.equals(vendor.getName()) && this.phone.equals(vendor.getPhone())
				&& this.address.equals(vendor.getAddress()) && this.officeName.equals(vendor.getOfficeName())) {

			// if callee's non-required attributes are not filled: return false
			if (vendor.getDescription() != null || vendor.getWebsite() != null || vendor.getEmail() != null)
				return false;
			return true;
		}
		return false;
	}

}
