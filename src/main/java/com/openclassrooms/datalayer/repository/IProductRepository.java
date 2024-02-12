package com.openclassrooms.datalayer.repository;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.datalayer.model.Product;


public interface IProductRepository extends CrudRepository<Product,Integer> {

}
