package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "reimbursement_type")
public class ReimbursementType {

	@Id
	@Column(name="type_id")
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private int id;
	
	@Column(name = "reim_type")
	private String type;

	public ReimbursementType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReimbursementType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public ReimbursementType(String type) {
		super();
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
	
	
}
