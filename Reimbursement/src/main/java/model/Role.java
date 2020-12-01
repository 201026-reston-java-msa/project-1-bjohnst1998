package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "user_roles")
public class Role {
@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY )
private int id;

@Column(name="role_name")
private String roleName;




public Role(int id, String roleName ) {
	super();
	this.id = id;
	this.roleName = roleName;

}

public Role( String roleName ) {
	super();
	
	this.roleName = roleName;

}
public Role( int roleId ) {
	super();
	
	this.id = roleId;

}
public Role() {
	super();
	// TODO Auto-generated constructor stub
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getRoleName() {
	return roleName;
}

public void setRoleName(String roleName) {
	this.roleName = roleName;
}







}
