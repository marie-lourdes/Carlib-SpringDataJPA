package com.openclassrooms.datalayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.openclassrooms.datalayer.model.Category;
import com.openclassrooms.datalayer.model.Comment;
import com.openclassrooms.datalayer.model.Product;
import com.openclassrooms.datalayer.service.CategoryService;
import com.openclassrooms.datalayer.service.CommentService;
import com.openclassrooms.datalayer.service.ProductService;

@SpringBootApplication
public class DatalayerApplication  implements CommandLineRunner{
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService; 
	
	@Autowired
	private CommentService commentService ;
	
	public static void main(String[] args) {
		SpringApplication.run(DatalayerApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {		
		List<Product> products = (List<Product>) productService.getProducts();
		products.forEach(product -> System.out.println(product.getName()));
		System.out.println("--------------------------------");
		
		List<Category> categories = (List<Category>)	categoryService.getCategories();
		categories.forEach(categorie -> System.out.println(categorie.getName()));
		System.out.println("--------------------------------");
		
		List<Comment> comments = (List<Comment>)	commentService.getComments();
		comments.forEach(comment-> System.out.println(comment.getContent()));
		
	}
}
