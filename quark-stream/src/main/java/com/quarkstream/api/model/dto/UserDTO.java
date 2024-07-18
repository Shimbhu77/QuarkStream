package com.quarkstream.api.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Size(min = 3,max = 40,message = "minimum 3 and maximum 40 characters is allowed for first name.")
    private String firstName;

    @Size(min = 3,max = 40,message = "minimum 3 and maximum 40 characters is allowed for last name.")
    private String lastName;

    @Column(unique = true)
    @Email(message = "please provide the valid email format.")
    private String email;

    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must be at least 8 characters long, contain digits, lower and upper case letters, special characters, and no whitespace"
    )
    private String password;

    private String userRole = "ROLE_USER";

    private String phone;
}