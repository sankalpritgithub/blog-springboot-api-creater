package com.sankalpspringboot.blog.blogspringbootapicreater.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.*;


public interface UserRepo extends JpaRepository<User ,Integer> {
    
    Optional<User> findByEmail(String email);
}
