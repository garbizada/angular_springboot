package com.company.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.company.model.Product;

@Service
public class ProductServiceImplement implements ProductService {

	public ProductServiceImplement() {

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

	@Override
	public void createProduct(Long productID, String productName, Integer price) {
		products.add(new Product(productID, productName, price));

	}

	@Override
	public void updateProduct(Product product) {
		getProduct(product.getProductID()).setProductPrice(product.getProductPrice());
		getProduct(product.getProductID()).setProductName(product.getProductName());
	}
	
	public void deleteProduct(Long id) {
		System.out.println("Status..."+ products.remove(getProduct(id)));
	}

}
