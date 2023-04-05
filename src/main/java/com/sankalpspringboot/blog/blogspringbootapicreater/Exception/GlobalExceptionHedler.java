package com.sankalpspringboot.blog.blogspringbootapicreater.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads.ApiResponse;

@RestControllerAdvice // is class ko exception healder bana degi is me kuch bhi exception ati h to sare method chal jayege 
public class GlobalExceptionHedler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
     String message=ex.getMessage();
     ApiResponse apiResponse= new ApiResponse(message,false);
     return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND); 
   }

   //validation error handler 
   
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
       Map<String, String> resp=new HashMap<>();
      // getBindingResult se hame List mil jayegi or us list ko terverse kar lege 
       ex.getBindingResult().getAllErrors().forEach((error)->{
        //error se object per filed nhi milegea to hum typecast karege filedError me 
          String fieldName = ((FieldError)error).getField();
        String message =  error.getDefaultMessage();
        resp.put(fieldName,message);
       });

       return new ResponseEntity<Map<String,String>>(resp, HttpStatus.BAD_REQUEST);

   }

}
