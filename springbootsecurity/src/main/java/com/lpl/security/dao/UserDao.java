package com.lpl.security.dao;


 import com.lpl.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Long>{

	User findByUsername(String username);
}
