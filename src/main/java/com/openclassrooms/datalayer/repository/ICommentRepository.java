package com.openclassrooms.datalayer.repository;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.datalayer.model.Comment;

public interface ICommentRepository extends CrudRepository<Comment,Integer> {

}
