package com.openclassrooms.datalayer.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private List<Comment> comments;
 
 
	// Add getters & setters
}