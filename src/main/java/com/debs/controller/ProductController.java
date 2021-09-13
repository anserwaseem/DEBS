package com.debs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.debs.model.Product;
import com.debs.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public String getProductsPage(Model model) {
//		System.out.println("\n\nproductService.getAllProducts().get(0):\n" + productService.getAllProducts().get(0));
		model.addAttribute("listProducts", productService.getAllProducts());
		return "products";
	}

	@GetMapping("/addProduct")
	public String getAddProductPage(Model model) {
		Product product = new Product();
		// addProduct page will access this empty product object for binding form data
		model.addAttribute("product", product);
		return "addProduct";
	}

	@GetMapping("/getUpdateProductPage/{id}") // id is the path variable that we binded in products.html inside form
	public String getUpdateProductPage(@PathVariable(value = "id") int id, Model model) {
		// add data to the model, then pass this model to the template (html file)

		// get product from the service, and fill it in updateProduct.html
		Product product = productService.getProductbyId(id);

		// set product as a model attribute to pre-populate the form
		model.addAttribute("product", product);
		return "updateProduct";
	}

	@PostMapping("/updateProduct")
	public String updateProduct(@ModelAttribute("product") Product product, Model model) {
		// all the form data will be binded to product (given parameter) object.
		// now, we will save product to database
		Product product1 = productService.getProductbyId(product.getId());
		if (!product1.equals(product))
			productService.saveProduct(product);
		return "redirect:/products";
	}

	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute("product") Product product, Model model) {
		// all the form data will be binded to product (given parameter) object.
		// now, we will save product to database
		productService.saveProduct(product);
		return "redirect:/products";
	}

	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(value = "id") int id, Model model) {
		// call delete product method
		this.productService.deleteProductById(id);

		model.addAttribute("listProducts", productService.getAllProducts());
		return "redirect:/products";
	}
}
