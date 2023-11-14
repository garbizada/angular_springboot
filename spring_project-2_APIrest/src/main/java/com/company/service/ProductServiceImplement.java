package com.company.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.company.model.Product;

@Service
public class ProductServiceImplement implements ProductService {

	public ProductsServiceImmplement() {
		
		products.add(new Product(1l, "iphone", 999));
		products.add(new Product(2l, "iphone XR", 2999));
		products.add(new Product(3l, "iphone 15", 5999));
	}

	List<Product> products = new ArrayList<Product>();

	
	public List<Product> getProducts() {
		return products;
	}

	public Product getProduct(Long id) {
		Iterator<Product> iterator = products.iterator();
		while (iterator.hasNext()) {
			Product product = iterator.next();
			if (product.getProductID().equals(id)) {
				return product;
			}
		}

		return null;
	}

}
