package com.openclassrooms.datalayer.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
			cascade=CascadeType.ALL,
			orphanRemoval=true,
			fetch= FetchType.EAGER
			)
	@JoinColumn(name="produit_id")
	private List<Comment> comments;
	
	@ManyToMany(
			mappedBy = "produits"
			)
	private List<Category> categories = new ArrayList<>();
 
	// Add getters & setters
}