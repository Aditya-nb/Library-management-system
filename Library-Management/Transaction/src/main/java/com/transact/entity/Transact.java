package com.transact.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.users.*;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@Entity @EntityListeners(AuditingEntityListener.class)
@Table(name = "Borrow")
public class Transact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer borrowId;
    Integer bookId;
    Integer userId;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using=JsonDataSerializer.class)
    Date issueDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using=JsonDataSerializer.class)
    Date returnDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using=JsonDataSerializer.class)
    Date dueDate;

	public void setIssueDate(Date currentDate) {
		// TODO Auto-generated method stub
		
	}

	public void setDueDate(Date overdueDate) {
		// TODO Auto-generated method stub
		
	}

	public void setReturnDate(Date currentDate) {
		// TODO Auto-generated method stub
		
	}

	public Object getBorrowId() {
		// TODO Auto-generated method stub
		return borrowId;
	}

	public Object getBookId() {
		// TODO Auto-generated method stub
		return bookId;
	}

	public Object getUserId() {
		// TODO Auto-generated method stub
		return userId;
	}

}
