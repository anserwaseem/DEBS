package com.debs.service;

import java.util.List;

import com.debs.model.Product;

public interface ProductService {
	List<Product> getAllProducts();

	void saveProduct(Product product);

	Product getProductbyId(long id);

	void deleteProductById(long id);
}
