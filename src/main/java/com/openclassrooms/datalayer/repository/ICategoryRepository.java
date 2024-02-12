package com.openclassrooms.datalayer.repository;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.datalayer.model.Category;

public interface ICategoryRepository extends CrudRepository<Category,Integer>  {

}
