package com.openclassrooms.datalayer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.datalayer.model.Category;
import com.openclassrooms.datalayer.repository.ICategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private ICategoryRepository categoryRepository;
	
	public Iterable<Category> getCategories(){
		return categoryRepository.findAll();
	}

	public Optional<Category> getCategoryById(Integer id) {
		return categoryRepository.findById(id);
	}
}
