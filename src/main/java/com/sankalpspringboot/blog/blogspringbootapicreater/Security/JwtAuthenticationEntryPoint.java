package com.sankalpspringboot.blog.blogspringbootapicreater.Security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//9:50
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    //unatherizated person hit the our api 
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Access Denined !!");
    }
     
}
//9:50