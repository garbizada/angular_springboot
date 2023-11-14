package com.company.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.company.model.Product;

@Service
public class ProductServiceImplement implements ProductService{

	@Override
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product(1l, "iphone", 999));
		products.add(new Product(2l, "iphone XR", 2999));
		products.add(new Product(3l, "iphone 15", 5999));
		return products;
	}

}
