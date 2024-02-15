package com.openclassrooms.datalayer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.openclassrooms.datalayer.model.Product;

public interface IProductRepository extends CrudRepository<Product,Integer> {
	
	//Derived Queries
	public Iterable<Product> findByName(String name);
	
	public Iterable<Product> findByCategoriesName(String name);//cher le product par le nom de la categorie, dans la l attribut list "categories"
	
	public Iterable<Product> findByCostLessThan(Integer cost);
	
	//requêtes JPQL
	@Query("FROM Product WHERE name = ?1")
    public Iterable<Product> findByNameJPQL(String name);
	
	//requêtes natives
	@Query(value = "SELECT * FROM produit WHERE cout = :cout", nativeQuery = true)
	public Iterable<Product> findByCostNative(@Param("cout") Integer cost);
}
