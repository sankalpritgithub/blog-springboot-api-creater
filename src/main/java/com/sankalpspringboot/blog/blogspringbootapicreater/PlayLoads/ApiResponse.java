package com.sankalpspringboot.blog.blogspringbootapicreater.PlayLoads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    
    private String message;
    private boolean success;

}