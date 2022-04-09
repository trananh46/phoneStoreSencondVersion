package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.DTO.CommentAndInformation;
import com.example.demo.DTO.count;
import com.example.demo.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

	@Query(value = "SELECT COUNT(id_comment) AS soLuong from comment WHERE id_phone_product = :idPhoneProduct",nativeQuery = true)
	count countTotalComment(@Param("idPhoneProduct") Long idPhoneProduct);
	
	@Query(value = "SELECT c.*,cs.name_customer,p.name_phone_product FROM `comment` c INNER JOIN customer cs ON c.id_customer = cs.id_customer INNER JOIN phone_product p ON c.id_phone_product = p.id_phone_product WHERE id_comment_detail IS NULL AND c.id_staff IS NULL AND c.id_phone_product = :idPhoneProduct ORDER BY (c.id_comment) DESC",nativeQuery = true)
	List<CommentAndInformation> displayMainCommentByIdProduct(@Param("idPhoneProduct") Long idPhoneProduct);
	
	@Query(value = "SELECT c.*,s.name_staff,p.name_phone_product FROM `comment` c INNER JOIN staff s ON c.id_staff = s.id_staff INNER JOIN phone_product p ON c.id_phone_product = p.id_phone_product WHERE id_comment_detail IS NOT NULL AND c.id_customer IS NULL AND c.id_phone_product = :idPhoneProduct",nativeQuery = true)
	List<CommentAndInformation> displaySecondCommentByIdProduct(@Param("idPhoneProduct") Long idPhoneProduct);
	
	@Query(value = "SELECT c.*,cs.name_customer,p.name_phone_product FROM `comment` c INNER JOIN customer cs ON c.id_customer = cs.id_customer INNER JOIN phone_product p ON c.id_phone_product = p.id_phone_product WHERE id_comment_detail IS NULL AND c.id_staff IS NULL ORDER BY (c.id_comment) DESC",nativeQuery = true)
	List<CommentAndInformation> displayAllCommentOfCustomer();
	
	
	@Query(value = "SELECT c.*,cs.name_customer,p.name_phone_product FROM `comment` c INNER JOIN customer cs ON c.id_customer = cs.id_customer INNER JOIN phone_product p ON c.id_phone_product = p.id_phone_product WHERE id_comment_detail IS NULL AND c.id_staff IS NULL AND c.id_comment = :idComment",nativeQuery = true)
	CommentAndInformation findMainCommentByIdComment(@Param("idComment") Long idComment);
	
	@Query(value = "SELECT c.*,s.name_staff,p.name_phone_product FROM `comment` c INNER JOIN staff s ON c.id_staff = s.id_staff INNER JOIN phone_product p ON c.id_phone_product = p.id_phone_product WHERE id_comment_detail IS NOT NULL AND c.id_customer IS NULL AND c.id_comment_detail = :idComment",nativeQuery = true)
	List<CommentAndInformation> displaySecondCommentByIdComment(@Param("idComment") Long idComment);
	
	@Query(value = "SELECT * FROM comment WHERE id_comment = :idComment",nativeQuery = true)
	Comment findCommentByIdComment(@Param("idComment") Long idComment);
	
	
	@Query(value = "SELECT c.*,cs.name_customer,p.name_phone_product FROM `comment` c INNER JOIN customer cs ON c.id_customer = cs.id_customer INNER JOIN phone_product p ON c.id_phone_product = p.id_phone_product WHERE id_comment_detail IS NULL AND c.id_staff IS NULL AND c.trang_thai = 0 ORDER BY (c.id_comment) DESC",nativeQuery = true)
	List<CommentAndInformation> displayListCommentOfCustomerHasNotReplied();
	
	@Query(value = "SELECT c.*,cs.name_customer,p.name_phone_product FROM `comment` c INNER JOIN customer cs ON c.id_customer = cs.id_customer INNER JOIN phone_product p ON c.id_phone_product = p.id_phone_product WHERE id_comment_detail IS NULL AND c.id_staff IS NULL AND c.trang_thai = 1 ORDER BY (c.id_comment) DESC",nativeQuery = true)
	List<CommentAndInformation> displayListCommentOfCustomerReplied();
	
	@Query(value = "DELETE FROM comment WHERE id_comment_detail = :idCommentDetail",nativeQuery = true)
	@Modifying
	@Transactional
	void deleteSecondCommentByIdCommentDetail(@Param("idCommentDetail") Long idCommentDetail);
	
	@Query(value = "DELETE FROM comment WHERE id_comment = :idComment",nativeQuery = true)
	@Modifying
	@Transactional
	void deleteCommentById(@Param("idComment") Long idComment);
}
