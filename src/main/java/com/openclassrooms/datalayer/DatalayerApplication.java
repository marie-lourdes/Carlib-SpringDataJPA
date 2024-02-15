package com.openclassrooms.datalayer;

import java.util.ArrayList;
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
		/*---------------------REQUETE CRUD-------------------------------ne pas executer plusieurs fois au lancement de l application*/

		/*List<Product> products = (List<Product>) productService.getProducts();
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

		System.out.println("***********************Creation****************");

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

		// methodes helpers lors de l ajout du produit, on ajoute le product dans
		// categorie qui est du coté Category avec Jointable
		// on ajoute le product dans Category lors de la creation du product pour
		// synchroniser les 2 objets mais sans l id generé SQL de new product(avant la
		// persitence sql save())
		newCategory.addProduct(newProduct);

		newProduct = productService.saveProduct(newProduct);
		productService.getProducts()
				.forEach(product -> System.out.println("list products with new product created " + product.getName()));

		// Verification de la bidirectionnalité lors de la creation du new product crée
		// avec l objet associé Category retourné avec l id generé de product
		newProduct.getCategories().forEach(category -> System.out
				.println(" all categories of new product created relation manyToMany  " + category.getName()));
		System.out.println("--------------------------------");

		Comment newComment = new Comment();
		newComment.setContent(" Comment created,Ce qu'on peut attendre d''une assurance au tiers, ni plus, ni moins");

		// method helper on ajoute le comment dans Product, du coté de @OneToMany, lors
		// de la creation du comment pour synchroniser les 2 objets mais sans l id
		// generé SQL de newComment(avant la persitence sql save())
		newProduct.addComment(newComment);

		newComment = commentService.addComment(newComment);
		commentService.getComments().forEach(
				comment -> System.out.println("list comments with new comment created " + comment.getContent()));

		// Verification de la bidirectionnalité lors de la creation du new product crée
		// avec l objet associé Coment retourné avec l id generé de product
		newProduct.getComments().forEach(comment -> System.out
				.println(" all comments of new product created relation OneToMany " + comment.getContent()));

		System.out.println("***********************Mise à jour****************");

		// Update Product id=1
		Product existingProduct = productService.getProductById(1).get();
		System.out.println(existingProduct.getCost());

		existingProduct.setCost(3000);
		productService.saveProduct(existingProduct);

		existingProduct = productService.getProductById(1).get();
		System.out.println(existingProduct.getCost());

		System.out.println("***********************Suppression****************");

		// DeleteCategory id=1 sans suppresion de product associé
		List<Product> productsPresentWithCategoryRemove = categoryService.getCategoryById(1).get().getProducts();
		productsPresentWithCategoryRemove.forEach(
				product -> System.out.println(" products of category id 1 before removing " + product.getName()));

		categoryService.deleteCategoryById(1);

		// verification de la non suppression des produits associé a la categorie
		// supprimé
		List<Product> verifProductsPresentWithCategoryRemove = (List<Product>) productService.getProducts();
		verifProductsPresentWithCategoryRemove.forEach(
				product -> System.out.println(" products of ex category id 1 after removing " + product.getName()));

		// DeleteProduct id=1 sans suppression de la cetgory associé et avec suppression
		// du commentairs associé
		Product productToRemove = productService.getProductById(1).get();
		List<Comment> existingCommentsOfProductToRemove = productToRemove.getComments();

		// method helper on supprime les commentaires associé en meme temps que la
		// suppression du product
		existingCommentsOfProductToRemove.forEach(comment -> productToRemove.removeComment(comment));

		productService.deleteProductById(1);

		// verification de la non suppression des categories associé au produit supprimé
		List<Category> verifCategoriesPresentWithProductRemove = (List<Category>) categoryService.getCategories();
		verifCategoriesPresentWithProductRemove.forEach(category -> System.out
				.println("all categories  of ex product id 1 after removing " + category.getName()));

		// DeleteComment id=1 sans suppression du produit associé
		Comment commentToRemove = commentService.getCommentById(1).get();
		Product existingProductOfCommentToRemove = commentToRemove.getProduct();
		// method helper on supprime le commentaire id 1 qui ne supprimera pas le
		// product associé via le cascade type merge et persist mais pas ALL
		// Suppresion du comment dans la list de Product
		existingProductOfCommentToRemove.removeComment(commentToRemove);

		commentService.deleteCommentById(1);

		// verification de la non suppression du produit associé au commentaire supprimé
		Product verifProductByIdPresentWithCommentRemove = productService
				.getProductById(existingProductOfCommentToRemove.getProductId()).get();
		System.out
				.println("product of comment id 1 after removing" + verifProductByIdPresentWithCommentRemove.getName());*/

		
		/*---------------------REQUETE AVANCEES-------------------------------*/
		
		System.out.println("***********************Derived Queries ****************");
		Iterable<Product> searchResultsByName = productService.getProductsByName("AssuranceTousRisques");
		searchResultsByName.forEach(
				resultProduct -> System.out.println("resultProduct id found  by name " + resultProduct.getProductId()));

		List<Product> searchResults = new ArrayList<Product>();
		searchResults = (List<Product>) productService.getProductsByCategoryName("Standard");
		searchResults.forEach(product -> System.out.println(product.getName()));

		searchResults = (List<Product>) productService.getProductsByCostLessThan(1000);
		searchResults.forEach(product -> System.out.println(product.getName()));

		Iterable<Category> searchCategory = categoryService.getCategoryByName("Standard");
		searchCategory.forEach(category -> System.out.println(category.getCategoryId()));

		searchCategory = categoryService.getCategoriesByProductName("AssuranceTousRisques");
		searchCategory.forEach(category -> System.out.println(category.getName()));

		Iterable<Comment> searchComments = commentService.getCommentContaining("deçu");
		searchComments.forEach(comment -> System.out.println(comment.getContent()));
	}
}
