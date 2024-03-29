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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "categorie")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="categorie_id")
	private int categoryId;
	
	@Column(name="nom")
	private String name;
	
	@ManyToMany(
			cascade= {
					CascadeType.PERSIST,// pour toutes les actions de creation d une categorie il y aura un impact sur les produits associé
					CascadeType.MERGE// pour toutes les actions de modification d une categorie il y aura un impact sur les produits associé
			},
			fetch=FetchType.LAZY // lors de la requete  d une categorie on ne recupere pas tous les produits associé contrairement au fetchType.EAGER, le LAZY requiert l annotation @Transactional
			)
	@JoinTable(//table de liaison
			name = "categorie_produit",
			joinColumns = @JoinColumn(name = "categorie_id"), 	
			inverseJoinColumns = @JoinColumn(name = "produit_id")
	)
	private List<Product> products = new ArrayList<>();

//methode utilitaire helpers pour la synchronisation des 2 objets associés	
	public void addProduct(Product product) {
		products.add(product);
		product.getCategories().add(this);
	}
 
	public void removeProduit(Product product) {
		products.remove(product);
		product.getCategories().remove(this);
	}

}
