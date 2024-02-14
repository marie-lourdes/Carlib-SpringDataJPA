package com.openclassrooms.datalayer.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "produit")
public class Product {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "produit_id")
	private int productId;
 
	@Column(name = "nom")
	private String name;
 
	@Column(name = "description")
	private String description;
 
	@Column(name = "cout")
	private int cost;
	
	@OneToMany(
			mappedBy="product",
			cascade=CascadeType.ALL,
			orphanRemoval=true
			)
	private List<Comment> comments =new ArrayList<>();
	
	@ManyToMany(
			mappedBy = "products",
			cascade=CascadeType.ALL
			)
	private List<Category> categories = new ArrayList<>();
 
	// Add getters & setters
	
	//methode utilitaire helpers pour la synchronisation des 2 objets associés	
	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setProduct(this);
	}
 
	public void removeComment(Comment comment) {
		comments.remove(comment);
		comment.setProduct(null);
	}

}