package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EntityBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column
	String title;
	
	@Column
	String content;
	
	@Column
	String author;
	
	@Column
//	@DateTimeFormat(pattern = "yyyy-mm-dd")
	String date;
	
	public EntityBoard(int id, String Title, String Content, String Author, String Date) {
		super();
		this.id = id;
		this.title = Title;
		this.content = Content;
		this.author = Author;
		this.date = Date;
	}
	
	public EntityBoard() {
		this.id = -1;
		this.title = "";
		this.content = "";
		this.author = "";
		this.date = "";
	}
	
	@Override
	public String toString() {
		return "EntityBoard{" + 
				"Title='" + title + '\'' + 
				", Id=" + id + '\'' +
				", Content=" + content + '\'' + 
				", Author=" + author + '\'' + 
				", Date=" + date + '\'' +
				'}';
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
