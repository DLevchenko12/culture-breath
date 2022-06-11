package com.culturebreathexhibitionsback.dto;

import com.culturebreathexhibitionsback.model.Role;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Value
public class AdminDto {

    UUID id;

    @NotBlank(message = "First name may not be blank")
    String firstName;

    @NotBlank(message = "Second name may not be blank")
    String secondName;

    @NotBlank(message = "Role may not be blank")
    Role role;

    @NotBlank(message = "Email may not be blank")
    String email;

    @NotBlank(message = "Not blank")
    String password;
}
