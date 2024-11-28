package com.crm.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentDto{
    private Long id;
    @NotBlank
    @Size(min = 3, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    @Email
    private String emailId;
    @Size(min = 10, max = 10, message = "Should be 10 Digits")
    private String phone;
    private Date date;
}
