package com.users.entity;

import lombok.Data;
import java.util.Set;

import jakarta.persistence.*;


@Data
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String username;
    private String name;
    private String password;
    
    
    public Users() {
    	
    }
    
    public Users(String username, String name, String password, Set<Roles> role) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
		this.role = role;
	}
    
    

	public Integer getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Set<Roles> getRole() {
		return role;
	}
//	public void setRole(Set<Roles> role) {
//		this.role = role;
//	}


	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Roles> role;


	public void setRole(Set<Roles> role2) {
		// TODO Auto-generated method stub
		
	}

}

