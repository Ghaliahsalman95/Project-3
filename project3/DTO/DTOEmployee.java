package com.example.project3.DTO;

import com.example.project3.Model.MyUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DTOEmployee {

    private String position;
    //@NotEmpty(message = "Salary cannot be null")
    //@Positive(message = "Salary must be a non-negative number")
    //@Min(value = 3000,message = "Salary must be greater than 3000")
    private Integer salary;

//@NotEmpty(message = "Username cannot be null")
//@Size(min = 4, max = 10, message = "Username length must be between 4 and 10 characters")
    private String username;

    //@NotEmpty(message = "Password cannot be null")
//@Size(min = 6,max = 10,message = "Password length must be at least 6 characters")
    private String password;

    //@NotEmpty(message = "Name cannot be null")
//@Size(min = 2, max = 20, message = "Name length must be between 2 and 20 characters")
    private String name;
    //@NotEmpty(message = "Email cannot be null")
//@Email(message = "Email must be a valid email format")
    private String email;


}
