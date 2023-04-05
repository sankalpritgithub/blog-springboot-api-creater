package com.sankalpspringboot.blog.blogspringbootapicreater.Imples;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.Category;
import com.sankalpspringboot.blog.blogspringbootapicreater.Exception.ResourceNotFoundException;
import com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads.CategoryDto;
import com.sankalpspringboot.blog.blogspringbootapicreater.Repositories.CategoryRepo;
import com.sankalpspringboot.blog.blogspringbootapicreater.Services.CategoryService;


@Service
public class CatoryServiceImp implements CategoryService{
     

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
     


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        // TODO Auto-generated method stub
     Category cat = this.modelMapper.map(categoryDto, Category.class);
     Category addedCat = this.categoryRepo.save(cat);

    return this.modelMapper.map(addedCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updCategoryDto(CategoryDto categoryDto, Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updateCat = this.categoryRepo.save(cat);
        
        return this.modelMapper.map(updateCat, CategoryDto.class);
    

    }

    @Override
    public void deleteCategory(Integer categoryId) {
       
     Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
          
     this.categoryRepo.delete(cat);
       // throw new UnsupportedOperationException("Unimplemented method 'deleteCategory'");
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        // TODO Auto-generated method stub
       Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
      
      return this.modelMapper.map(cat,CategoryDto.class);
       // throw new UnsupportedOperationException("Unimplemented method 'getCategory'");
    }

    @Override
    public List<CategoryDto> getCategories() {
        // TODO Auto-generated method stub
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> catDtos = categories.stream().map((cat)-> this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return catDtos;
       
    }
    
}
