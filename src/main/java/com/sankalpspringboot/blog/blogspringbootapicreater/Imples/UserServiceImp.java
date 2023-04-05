package com.sankalpspringboot.blog.blogspringbootapicreater.Imples;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sankalpspringboot.blog.blogspringbootapicreater.Entites.User;
import com.sankalpspringboot.blog.blogspringbootapicreater.Exception.ResourceNotFoundException;
import com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads.UserDto;
import com.sankalpspringboot.blog.blogspringbootapicreater.Repositories.UserRepo;
import com.sankalpspringboot.blog.blogspringbootapicreater.Services.UserService;

@Service
public class UserServiceImp implements UserService{
       
     @Autowired
      private UserRepo userRepo;
      
      @Autowired
      private ModelMapper modelMapper;



    @Override
    public UserDto createUser(UserDto userDto) {
      
        User user = this.dtoToUser(userDto);

        User savUser = this.userRepo.save(user);
          return this.userToDto(savUser);

    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
         
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
 
       User updateUser  = this.userRepo.save(user);
      UserDto userDto1 = this.userToDto(updateUser);
      return userDto1;
 
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId)
                 .orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();

      List<UserDto> userDtos =  users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
      return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        this.userRepo.delete(user);
    }
    

    public User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        //  user.setId(userDto.getId());
        //  user.setName(userDto.getName());
        //  user.setEmail(userDto.getEmail());
        //  user.setAbout(userDto.getAbout());
        //  user.setPassword(userDto.getPassword());
 
         return user;
     }

     public UserDto userToDto(User user){

        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        // userDto.setId(user.getId());
        // userDto.setName(user.getName());
        // userDto.setEmail(user.getEmail());
        // userDto.setAbout(user.getAbout());
        // userDto.setPassword(user.getPassword());
    
        return userDto;
    }

    // aab hum vaidation ka concept shik rhe h 
    // java bean si validation with JSR 380 knows as a Bean Validation 2.0
    // for Validation different annotation is used like @NotNull @Min @Size etc.


}
