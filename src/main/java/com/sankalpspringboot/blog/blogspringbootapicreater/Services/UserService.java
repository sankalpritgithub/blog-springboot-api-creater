package com.sankalpspringboot.blog.blogspringbootapicreater.Services;

import java.util.List;

import com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads.UserDto;

public interface UserService {
    
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
     void deleteUser(Integer userId);
}
