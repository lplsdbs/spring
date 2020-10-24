package com.cloud.eurekaserver.service.impl;



import com.cloud.eurekaserver.Util;
import com.cloud.eurekaserver.client.AuthServiceClient;
import com.cloud.eurekaserver.dao.UserDao;
import com.cloud.eurekaserver.dto.UserLoginDTO;
import com.cloud.eurekaserver.entity.JWT;
import com.cloud.eurekaserver.entity.User;
import com.cloud.eurekaserver.exception.UserLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Created by fangzhipeng on 2017/5/10.
 */
@Service
public class UserServiceDetail implements UserDetailsService {

   @Autowired
   private UserDao userRepository;
   @Autowired
   AuthServiceClient client;
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return userRepository.findByUsername(username);
   }

   public User insertUser(String username, String  password){
      User user=new User();
      user.setUsername(username);
      user.setPassword(Util.BcryptPassword(password));
      return userRepository.save(user);
   }

   public UserLoginDTO login(String username, String password){
      User user=userRepository.findByUsername(username);
      if (null == user) {
         throw new UserLoginException("error username");
      }
      if(!Util.matchs(password,user.getPassword())){
         throw new UserLoginException("error password");
      }
      // 获取token
      JWT jwt=client.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==","password",username,password,"service");
      // 获得用户菜单
      if(jwt==null){
         throw new UserLoginException("error internal");
      }
      UserLoginDTO userLoginDTO=new UserLoginDTO();
      userLoginDTO.setJwt(jwt);
      userLoginDTO.setUser(user);
      return userLoginDTO;

   }
}
