package model;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;

public class ReimbursementDTO implements Serializable {
	private int id;
	private double amount;
	private String dateSubmitted;
	private String dateResolved;
	private String description;
	//private Blob recipt;
	private String user;
	private String resolver;
	private String status;
	private String type;
	public ReimbursementDTO(int id, double amount, String description, String user,String dateSubmitted,String resolver, String status,String type) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.user = user;
		this.dateSubmitted = dateSubmitted;
		this.resolver=resolver;
		this.status=status;
		this.type=type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDateSubmitted() {
		return dateSubmitted;
	}
	public void setDateSubmitted(String dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	public String getDateResolved() {
		return dateResolved;
	}
	public void setDateResolved(String dateResolved) {
		this.dateResolved = dateResolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getResolver() {
		return resolver;
	}
	public void setResolver(String resolver) {
		this.resolver = resolver;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
