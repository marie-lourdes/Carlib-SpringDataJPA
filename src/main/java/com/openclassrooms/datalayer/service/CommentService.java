package com.openclassrooms.datalayer.service;

import java.util.Optional;

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

	public Optional<Comment> getCommentById(Integer id) {
		return commentRepository.findById(id);
	}
	
	public Iterable<Comment> getCommentContaining(String expression) {
		return commentRepository.findByContentContaining(expression);
	}
	
	public Comment addComment(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public void deleteCommentById(Integer id) {
		commentRepository.deleteById(id);
	}
}
