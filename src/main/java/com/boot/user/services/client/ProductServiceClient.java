package com.boot.user.services.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import com.boot.user.services.model.Product;
import com.boot.user.services.util.Constants;

public class ProductServiceClient {

	static RestTemplate restTemplate = new RestTemplate();

	public static ResponseEntity<Product> callGetProductByProductName(String productName) {

			return restTemplate.getForEntity(Constants.GET_PRODUCT_BY_PRODUCT_NAME + productName, Product.class);
	}

}