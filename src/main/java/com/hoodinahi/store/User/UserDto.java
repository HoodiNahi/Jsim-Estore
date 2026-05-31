package com.hoodinahi.store.User;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {

    
    private Long id;
    private String name;
    private String email;
    // @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    // private LocalDateTime createdAt;
}
