package com.sankalpspringboot.blog.blogspringbootapicreater.Config;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sankalpspringboot.blog.blogspringbootapicreater.Security.CustomUserDetailService;
import com.sankalpspringboot.blog.blogspringbootapicreater.Security.JwtAuthenticationEntryPoint;
import com.sankalpspringboot.blog.blogspringbootapicreater.Security.JwtAuthenticationFilter;
@Configuration
public class SecurityConfig  {
    

  
  @Autowired
  private CustomUserDetailService customUserDetailService;

  @Autowired
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;


    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
        .cors().and()
        .csrf()
        .disable()
        .authorizeHttpRequests()
        
       // .antMatchers("/api/v1/auth/login")
        // .antMatchers("/api/v1/auth/login")
        //.permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and() // set a policy of stateManagment
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // constant rhaka hua h 
           
        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
       
        http.authenticationProvider(daoAuthenticationProvider());

        DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
         
        return defaultSecurityFilterChain;  

    }





   








  /* 
  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

   protected void configure(HttpSecurity http) throws Exception{

        http
        .csrf()
        .disable()
        .authorizeHttpRequests()
        .antMatchers("/api/v1/auth/login")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and() // set a policy of stateManagment
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // constant rhaka hua h 
           
        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
*/
/* 
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(this.customUserDetailService)
       .passwordEncoder(passwordEncoder());
    }
*/

  @Bean
   public DaoAuthenticationProvider daoAuthenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(this.customUserDetailService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;

   }  


    @Bean
    public PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
  }


  /* 
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception{
    return super.authenticationManagerBean();
  }
*/

@Bean
public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception{
  return configuration.getAuthenticationManager();
}
  



  
}
