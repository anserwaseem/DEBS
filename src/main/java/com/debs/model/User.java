package com.debs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

@Entity
@Table(name = "user", schema = "debs1")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	private String username;
	private String password;
	private String name;
	private String birthday;
	private String country;
	private String occupation;
	private String businessName;
	private String businessAddress;

	@Email(message = "Please enter a valid e-mail address")
	private String email;
	private String phone;
	private String website;
	private String aboutMe;
	@Transient
	private String newUsername;
	@Transient
	private String newPassword;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getNewUsername() {
		return newUsername;
	}

	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "id: " + id + " username: " + username + " password: " + password + " name: " + name + " businessName: "
				+ businessName + " businessAddress: " + businessAddress + " website: " + website + " email: " + email
				+ " phone: " + phone;
	}

	@Override
	public boolean equals(Object obj) {
		// If the object is compared with itself then return true
		if (obj == this) {
			return true;
		}

		// Check if obj is an instance of User or not "null instanceof [type]" also
		// returns false
		if (!(obj instanceof User)) {
			return false;
		}

		// typecast obj to User so that we can compare data members
		User user = (User) obj; // user is callee here
		System.out.println("\n\nthis: \n" + this);
		System.out.println("\n\nobj: \n" + user);

		// if caller's required attributes equal to callee's required attributes
		if (this.username.equals(user.getUsername()) && this.password.equals(user.getPassword())) {

			// if callee's non-required attributes are not filled: return false
			if (user.getWebsite() != null || user.getEmail() != null || user.getBirthday() != null
					|| user.getBusinessAddress() != null || user.getBusinessName() != null || user.getCountry() != null
					|| user.getName() != null || user.getOccupation() != null || user.getPhone() != null
					|| user.getAboutMe() != null)
				return false;
			return true;
		}
		return false;
	}
}
