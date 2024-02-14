package com.openclassrooms.datalayer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.datalayer.model.Category;
import com.openclassrooms.datalayer.model.Comment;
import com.openclassrooms.datalayer.model.Product;
import com.openclassrooms.datalayer.service.CategoryService;
import com.openclassrooms.datalayer.service.CommentService;
import com.openclassrooms.datalayer.service.ProductService;

@SpringBootApplication
public class DatalayerApplication implements CommandLineRunner {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CommentService commentService;

	public static void main(String[] args) {
		SpringApplication.run(DatalayerApplication.class, args);
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {

		List<Product> products = (List<Product>) productService.getProducts();
		products.forEach(product -> System.out.println("all products " + product.getName()));
		System.out.println("--------------------------------");

		List<Category> categories = (List<Category>) categoryService.getCategories();
		categories.forEach(categorie -> System.out.println("all categories " + categorie.getName()));
		System.out.println("--------------------------------");

		List<Comment> comments = (List<Comment>) commentService.getComments();
		comments.forEach(comment -> System.out.println("all comments " + comment.getContent()));
		System.out.println("--------------------------------");

		Optional<Product> optProduct = productService.getProductById(1);
		Product productId1 = optProduct.get();
		System.out.println("product 1" + productId1.getName());

		productId1.getComments()
				.forEach(comment -> System.out.println(
						" all comments of product 1 relation OneToMany bidirectionnelle avec  l entité Comment "
								+ comment.getContent()));

		productId1.getCategories()
				.forEach(category -> System.out.println(
						" all categories of product 1 relation manyToMany biderectionnelle avec  l entité Category "
								+ category.getName()));
		System.out.println("--------------------------------");

		Optional<Category> optCategory = categoryService.getCategoryById(1);
		Category categoryId1 = optCategory.get();
		System.out.println("category 1" + categoryId1.getName());

		categoryId1.getProducts()
				.forEach(product -> System.out.println(
						"all products of categoryId1 relation manyToMany bidirectionnelle avec l entité product "
								+ product.getName()));
		System.out.println("--------------------------------");

		Optional<Comment> optComment = commentService.getCommentById(1);
		Comment CommentId1 = optComment.get();
		System.out.println("comment 1" + CommentId1.getContent());
		System.out.println(" a product  of a comment1 relation ManyToOne bidirectionnelle avec  l entité Product "
				+ CommentId1.getProduct().getName());
		System.out.println("--------------------------------");

		System.out.println("---------Creation----------");

		Category newCategory = new Category();
		newCategory.setName("Promotion");

		newCategory = categoryService.addCategory(newCategory);
		categoryService.getCategories().forEach(
				categorie -> System.out.println("list categories with new category created " + categorie.getName()));
		System.out.println("--------------------------------");

		Product newProduct = new Product();
		newProduct.setName("AssuranceCreated");
		newProduct.setCost(2400);
		newProduct.setDescription("Assurance créee");

		newProduct = productService.addProduct(newProduct);
		productService.getProducts()
				.forEach(product -> System.out.println("list products with new product created " + product.getName()));
		System.out.println("--------------------------------");

	}
}
