package com.sankalpspringboot.blog.blogspringbootapicreater;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
// ye wali calss hame configer anotation provide karti h 

@SpringBootApplication
public class BlogSpringbootApiCreaterApplication {

	

	public static void main(String[] args) {
		SpringApplication.run(BlogSpringbootApiCreaterApplication.class, args);
	}
    @Bean // spring container iske object create karde jaha bhi hum iska use kare rhe h 
	public ModelMapper modelMapper(){
        return new ModelMapper();
	}

// create a encrypted password to implement commandLineRunner
 /* 
	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("abc"));
	}
*/
	
}
