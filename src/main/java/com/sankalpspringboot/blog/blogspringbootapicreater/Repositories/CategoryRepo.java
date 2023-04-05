package com.sankalpspringboot.blog.blogspringbootapicreater.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
    
}
