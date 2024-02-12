package com.openclassrooms.datalayer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.datalayer.model.Product;
import com.openclassrooms.datalayer.repository.IProductRepository;

@Service
public class ProductService {
	@Autowired
	private IProductRepository productRepository;
	
	public Iterable<Product> getProducts() {
		return productRepository.findAll();
	}
}
