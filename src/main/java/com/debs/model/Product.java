package com.debs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product", schema = "debs1")
public class Product {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
//	private String quantity;
	private String openingStock;
	private String costPrice;
	private String salePrice;

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

//	public String getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(String quantity) {
//		this.quantity = quantity;
//	}

	public String getOpeningStock() {
		return openingStock;
	}

	public void setOpeningStock(String openingStock) {
		this.openingStock = openingStock;
	}

	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	@Override
	public String toString() {
		return "id: " + id + " name: " + name + " description: " + description;
	}

	@Override
	public boolean equals(Object obj) {
		// If the object is compared with itself then return true
		if (obj == this) {
			return true;
		}

		// Check if obj is an instance of Asset or not "null instanceof [type]" also
		// returns false
		if (!(obj instanceof Asset)) {
			return false;
		}

		// typecast obj to Customer so that we can compare data members
		Asset asset = (Asset) obj; // asset is callee here

		// if caller's required attributes equal to callee's required attributes
		if (this.name.equals(asset.getName())) {

			// if callee's non-required attributes are not filled: return false
			if (asset.getDescription() != null)
				return false;
			return true;
		}
		return false;
	}

}
