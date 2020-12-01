package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "reimbursement_status")
public class ReimbursementStatus {
	@Id
	@Column(name="status_id")
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private int id;
	
	@Column(name = "reim_status")
	private String status;

	public ReimbursementStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	public ReimbursementStatus(String status) {
		super();
		this.status = status;
	}
	public ReimbursementStatus()
	{
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
