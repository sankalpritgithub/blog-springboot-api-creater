package com.sankalpspringboot.blog.blogspringbootapicreater.Controller;

import org.hibernate.jpa.internal.ExceptionMapperLegacyJpaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads.JwtAuthRequest;
import com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads.JwtAuthResponse;
import com.sankalpspringboot.blog.blogspringbootapicreater.Security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    
   // generate Token Helper 
   @Autowired
   private JwtTokenHelper jwtTokenHelper;
   @Autowired
   private UserDetailsService userDetailsService;
   @Autowired
private AuthenticationManager authenticationManager;


   @PostMapping("/login")
   public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
      
   //create a method in this Class 
      this.authentication(request.getUsername(),request.getPassword());

    UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

    String token = this.jwtTokenHelper.generateToken(userDetails);

    JwtAuthResponse response = new JwtAuthResponse();
    response.setToken(token);

    return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
}


private void authentication(String username, String password) throws Exception {

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
    // autherticate karege to hame username password pass karna hoga 
    //or ye authenticate kar dega username pass sahi h ya galat 

       try{
        this.authenticationManager.authenticate(authenticationToken);
       }catch(BadCredentialsException e){
         System.out.println("Invalid Details !!");
         throw new Exception("Invalid Username Or Password !!");
       }
      
}
}


