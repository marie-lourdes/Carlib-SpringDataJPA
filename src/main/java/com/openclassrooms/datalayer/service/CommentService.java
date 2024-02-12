package com.openclassrooms.datalayer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.datalayer.model.Comment;
import com.openclassrooms.datalayer.repository.ICommentRepository;

@Service
public class CommentService {
	@Autowired
	private ICommentRepository commentRepository;
	
	public Iterable<Comment> getComments(){
		return commentRepository.findAll();
	}
}
