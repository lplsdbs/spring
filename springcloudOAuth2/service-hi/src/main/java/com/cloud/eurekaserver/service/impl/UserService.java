package com.cloud.eurekaserver.service.impl;

import com.cloud.eurekaserver.dao.UserDao;
import com.cloud.eurekaserver.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserService  {
   private static final BCryptPasswordEncoder en=new BCryptPasswordEncoder();


   private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

   @Autowired
   private UserDao userDao;


   public User create(String username, String password) {

      User user=new User();
//      user.setId(5L);
      user.setUsername(username);
      String hash = encoder.encode(password);
      user.setPassword(hash);
      User u=userDao.save(user);
      return u;

   }

}
