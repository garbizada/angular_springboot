package com.company.controller;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.Product;
import com.company.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping(value = "gettemperature")
	public ResponseEntity<String> handle2() {

		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		int temperature = (int) (Math.random() * (60 - 10)) + 10;

		String testBody = "<h3>Current temperature : " + temperature + "Degrees!! </h3>"
				+ "<h3>Response from server received at: "
				+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
				+ "</h3> <a href=''> Re-send the request </a>";
		return ResponseEntity.ok().cacheControl(cacheControl).body(testBody);
	}

	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	List<Product> getProducts() {
		return productService.getProducts();
	}

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable("id") Long id) {
		return productService.getProduct(id);
	}

	@PostMapping(value = "")
	public Map<String, Object> createProduct(@RequestParam(value = "id") Long id,
			@RequestParam(value = "name") String name, @RequestParam(value = "price") Integer price) {

		productService.createProduct(id, name, price);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "Product added!");
		return map;
	}

	@PutMapping(value = "")
	public Product updateProductUsingJson(@RequestBody Product product) {
		productService.updateProduct(product);
		return product;
	}

	@DeleteMapping("/{id}")
	public Map<String, Object> deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "Product deleted!");
		return map;

	}

	@PatchMapping(value = "/{id}")
	public @ResponseBody void saveManager(@PathVariable Long id, @RequestBody Map<Object, Object> fields) {
		Product product = productService.getProduct(id);
		fields.forEach((k, v) -> {
			java.lang.reflect.Field field = (Field) ReflectionUtils.findField(Product.class, (String) k);
			field.setAccessible(true);
			ReflectionUtils.setField(field, product, v);

		});
		productService.updateProduct(product);
	}
}
