package com.example.demo.DTO;

import java.time.LocalDate;

public interface CommentAndInformation {

	Long getId_comment();
	Long getId_staff();
	Long getId_phone_product();
	Long getId_customer();
	String getContent();
	LocalDate getDate();
	Long getId_comment_detail();
	Long getTrang_thai();
	String getName_customer();
	String getName_staff();
	String getName_phone_product();
}
