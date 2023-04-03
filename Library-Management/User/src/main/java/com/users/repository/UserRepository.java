package com.users.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer>{
	Optional<Users> findByUsername(String username);
	
}
