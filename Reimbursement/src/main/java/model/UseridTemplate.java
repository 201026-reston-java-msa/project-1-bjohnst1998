package model;

import java.io.Serializable;

public class UseridTemplate implements Serializable {
	int userId;

	public UseridTemplate(int userId) {
		super();
		this.userId = userId;
	}

	public UseridTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
}
