package com.cloud.eurekaserver.dao;


 import com.cloud.eurekaserver.entity.User;
 import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Long>{

	User findByUsername(String username);

}
