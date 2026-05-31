package com.hoodinahi.store.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "Name is required")
    @Size(max=255, message = "Name must be at most 255 characters")
    String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Lowercase(message = "Email must be in lowercase")
    String email;

    @NotBlank(message = "Password is required")
    @Size(min =6, max=255, message = "Password must be between 6 and 255 characters")
    String password;

}
