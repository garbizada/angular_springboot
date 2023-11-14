package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.Product;
import com.company.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("")
	List<Product> getProducts() {
		return productService.getProducts();
	}

	@GetMapping("/(id)")
	public Product getProduct(@PathVariable("id") Long id) {
		return productService.getProduct(id);
	}
}
