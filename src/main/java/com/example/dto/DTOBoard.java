package com.example.dto;

import com.example.entity.EntityBoard;

public class DTOBoard {
	public String Title;
	public String Content;
	public String Author;
	public String Date;
	
	public DTOBoard(String title, String content, String author, String date) {
		super();
		Title = title;
		Content = content;
		Author = author;
		Date = date;
	}
	
	public DTOBoard() {
		
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}
	
	public EntityBoard ToEntity() {
		return new EntityBoard(-1, this.Title, this.Content, this.Author, this.Date);
	}
	
	@Override
	public String toString() {
		return "DTOBoard [Title=" + Title + ", Content=" + Content + ", Author=" + Author + ", Date=" + Date + "]";
	}
	
	
}
