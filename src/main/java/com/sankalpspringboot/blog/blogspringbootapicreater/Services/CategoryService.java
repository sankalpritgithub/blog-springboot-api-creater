package com.sankalpspringboot.blog.blogspringbootapicreater.Services;

import java.util.List;

import com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads.CategoryDto;

public interface CategoryService {
    

    public CategoryDto createCategory(CategoryDto categoryDto);


    public CategoryDto updCategoryDto(CategoryDto categoryDto,Integer categoryId);

    public void deleteCategory(Integer categoryId);

    public CategoryDto getCategory(Integer categoryId);

    public List<CategoryDto> getCategories();

    
    
     


}
