package com.users.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Roles {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    private String roleName;
    
    
	public Roles(String roleName) {
		super();
		this.roleName = roleName;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
    

}
