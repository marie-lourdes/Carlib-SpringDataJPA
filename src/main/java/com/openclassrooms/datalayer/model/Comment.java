package com.openclassrooms.datalayer.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "commentaire")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="commentaire_id")
	private int commentId;
	
	@Column(name="contenu")
	private String content;

	@ManyToOne(
			cascade= {
					CascadeType.PERSIST,// pour toutes les actions de creation d une categorie il y aura un impact sur les produits associé
					CascadeType.MERGE// pour toutes les actions de modification d une categorie il y aura un impact sur les produits associé
			}                                      )
	@JoinColumn(name="produit_id")
	private Product product;
	
}
