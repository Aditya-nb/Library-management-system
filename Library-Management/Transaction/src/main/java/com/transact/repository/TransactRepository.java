package com.transact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.transact.entity.Transact;


@Repository
public interface TransactRepository extends JpaRepository<Transact,Integer>{
	List<Transact> findByUserId(Integer userId);
    List<Transact> findByBookId(Integer bookId);

}
