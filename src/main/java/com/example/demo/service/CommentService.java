package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CommentAndInformation;
import com.example.demo.DTO.count;
import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	public count countTotalComment(Long idPhoneProduct) {
		count count = commentRepository.countTotalComment(idPhoneProduct);
		return count;
	}
	
	public List<CommentAndInformation> displayMainCommentByIdProduct(Long idPhoneProduct){
		List<CommentAndInformation> listComment = commentRepository.displayMainCommentByIdProduct(idPhoneProduct);
		return listComment;
	}
	
	public List<CommentAndInformation> displaySecondCommentByIdProduct( Long idPhoneProduct){
		List<CommentAndInformation> listComment = commentRepository.displaySecondCommentByIdProduct(idPhoneProduct);
		return listComment;
	}
	
	public void insertCommentCustomer(Comment c) {
		Comment c1 = new Comment();
		c1.setContent(c.getContent());
		c1.setCustomer(c.getCustomer());
		c1.setDate(LocalDate.now());
		c1.setPhoneProduct(c.getPhoneProduct());
		// hasn't seen status of comment is 0
		Long status = (long)0;
		c1.setStatus(status);
		commentRepository.save(c1);
		
	}
	
	public List<CommentAndInformation> displayAllCommentOfCustomer(){
		List<CommentAndInformation> listComment = commentRepository.displayAllCommentOfCustomer();
		return listComment;
	}
	
	public CommentAndInformation findMainCommentByIdComment(Long idComment){
		CommentAndInformation mainComment = commentRepository.findMainCommentByIdComment(idComment);
		return mainComment;
	}
	
	public List<CommentAndInformation> displaySecondCommentByIdComment(Long idComment){
		List<CommentAndInformation> listComment = commentRepository.displaySecondCommentByIdComment(idComment);
		return listComment;
	}
	
	
	public void replyCommentOfCustomer(Comment c) {
		Comment c1 = new Comment();
		c1.setContent(c.getContent());
		c1.setDate(LocalDate.now());
		c1.setStaff(c.getStaff());
		c1.setIdCommentDetail(c.getIdCommentDetail());
		c1.setPhoneProduct(c.getPhoneProduct());
		commentRepository.save(c1);
		
		Comment c2 = commentRepository.findCommentByIdComment(c.getIdCommentDetail());
		// has seen status is 1
		Long status = (long)1;
		c2.setStatus(status);
		commentRepository.save(c2);
	}
	
	public List<CommentAndInformation> displayListCommentOfCustomerHasNotReplied(){
		List<CommentAndInformation> listComment = commentRepository.displayListCommentOfCustomerHasNotReplied();
		return listComment;
	}
	
	public List<CommentAndInformation> displayListCommentOfCustomerReplied(){
		List<CommentAndInformation> listComment = commentRepository.displayListCommentOfCustomerReplied();
		return listComment;
	}
	
	
	
	
	public void deleteCommentById(Long idComment) {
		commentRepository.deleteCommentById(idComment);
	}
	
	public void deleteMainCommentById(Long idComment) {
		commentRepository.deleteCommentById(idComment);
		commentRepository.deleteSecondCommentByIdCommentDetail(idComment);
	}
}
