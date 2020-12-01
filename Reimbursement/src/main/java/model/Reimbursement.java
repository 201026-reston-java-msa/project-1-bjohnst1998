package model;

import java.sql.Blob;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursements")
public class Reimbursement {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "reim_amount")
	private double amount;
	
	@Column(name="submitted")
	private LocalDateTime dateSubmitted;
	@Column(name = "resolved")
	private LocalDateTime dateResolved;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "recipt")
	private Blob recipt;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "associated_user")
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "resolving_user")
	private User resolver;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "status")
	private ReimbursementStatus status;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "type")
	private ReimbursementType type;

	public Reimbursement(int id, double amount, LocalDateTime dateSubmitted, LocalDateTime dateResolved,
			String description, Blob recipt, User user, User resolver, ReimbursementStatus status,
			ReimbursementType type) {
		super();
		this.id = id;
		this.amount = amount;
		this.dateSubmitted = dateSubmitted;
		this.dateResolved = dateResolved;
		this.description = description;
		this.recipt = recipt;
		this.user = user;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public Reimbursement(double amount,String description,
			Blob recipt, User user,ReimbursementStatus status, ReimbursementType type) {
		super();
		this.amount = amount;
		this.dateSubmitted = LocalDateTime.now();
		this.description = description;
		this.recipt = recipt;
		this.user = user;
		this.status = status;
		this.type = type;
	}

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
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

	public LocalDateTime getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(LocalDateTime dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public LocalDateTime getDateResolved() {
		return dateResolved;
	}

	public void setDateResolved(LocalDateTime dateResolved) {
		this.dateResolved = dateResolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getRecipt() {
		return recipt;
	}

	public void setRecipt(Blob recipt) {
		this.recipt = recipt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getResolver() {
		return resolver;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", dateSubmitted=" + dateSubmitted + ", dateResolved="
				+ dateResolved + ", description=" + description + ", recipt=" + recipt + ", user=" + user
				+ ", resolver=" + resolver + ", status=" + status + ", type=" + type + "]";
	}
	
	
	
	
	
}
