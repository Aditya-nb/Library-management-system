package com.books.entity;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "Books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer bookId;
    String bookName;
    String bookAuthor;
    String bookGenre;
    Integer noOfCopies;
    
    
    public Books() {
    	
    }

    public Books( String bookName, String bookAuthor, String bookGenre, Integer noOfCopies) {
		super();
		
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookGenre = bookGenre;
		this.noOfCopies = noOfCopies;
	}

	public Integer getBookId() {
		return bookId;
	}


	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookGenre() {
		return bookGenre;
	}

	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}

	public Integer getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	public void borrowBook() {
        this.noOfCopies--;
    }

    public void returnBook() {
        this.noOfCopies++;
    }

}
