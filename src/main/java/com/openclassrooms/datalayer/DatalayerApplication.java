package com.openclassrooms.datalayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.openclassrooms.datalayer.model.Product;
import com.openclassrooms.datalayer.service.ProductService;

@SpringBootApplication
public class DatalayerApplication  implements CommandLineRunner{
	@Autowired
	private ProductService productService;
	
	public static void main(String[] args) {
		SpringApplication.run(DatalayerApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {		
		Iterable<Product> products = productService.getProducts();
		products.forEach(product -> System.out.println(product.getName()));
	}
}
