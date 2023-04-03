package com.auth.entity;

import com.users.entity.Users;

public class Response {
	private Users user;
    private String jwtToken;

    public Response(Users user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}


