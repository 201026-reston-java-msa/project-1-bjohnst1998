package model;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;

public class ReimbursementDTO implements Serializable {
	private int id;
	private double amount;
	private LocalDateTime dateSubmitted;
	private LocalDateTime dateResolved;
	private String description;
	private Blob recipt;
	private User user;
	private User resolver;
	public ReimbursementDTO(int id, double amount, String description, User user) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.user = user;
		dateSubmitted = LocalDateTime.now();
	}
	
	

}
