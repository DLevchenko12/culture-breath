package com.culturebreathexhibitionsback.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Value
public class AuthorizedUserDto {

    UUID id;

    @NotBlank(message = "First name may not be blank")
    String firstName;

    @NotBlank(message = "Second name may not be blank")
    String secondName;

    @NotBlank(message = "Contact number may not be blank")
    String contactNumber;

    @NotBlank(message = "Email may not be blank")
    String email;
}
