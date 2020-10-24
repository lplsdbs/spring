package com.lpl.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//开启方法级别的安全支持
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;
    //创建一个内存中的认证用户信息

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
//        auth.userDetailsService(userDetailsService());
        //从数据库获取用户角色信息
        auth.userDetailsService(userDetailsService);
//        auth.inMemoryAuthentication().withUser("lpl").password("123").roles("USE");
    }
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        //内存中存放用户信息
//        InMemoryUserDetailsManager manager=new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("lpl").password("123").roles("USE").build());
//        manager.createUser(User.withUsername("admin").password("123").roles("admin","USE").build());
//        return manager;
//    }
    @Override
    //配置了首页登录页用户首页等
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //以css,index开头的资源不需要验证
                .antMatchers("/css/**","/index").permitAll()
                //以user，blogs开头的资源需要USE的角色
                .antMatchers("/user/**").hasAnyRole("USER")
                .antMatchers("/blogs/**").hasAnyRole("USER")
                .and()
                //表单的登录地址
                .formLogin().loginPage("/login").failureUrl("/login-error")
                .and()
                //异常处理重定向
                .exceptionHandling().accessDeniedPage("/401");
        //注销登录成功重定向到首页
        http.logout().logoutSuccessUrl("/");
//        super.configure(http);
    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/css/**", "/index").permitAll()
//                .antMatchers("/user/**").hasRole("USER")
//                .antMatchers("/blogs/**").hasRole("USER")
//                .and()
//                .formLogin().loginPage("/login").failureUrl("/login-error")
//                .and()
//                .exceptionHandling().accessDeniedPage("/401");
//        http.logout().logoutSuccessUrl("/");
//    }
}
