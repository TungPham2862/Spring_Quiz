package com.example.springquiz.model.dto;

import com.example.springquiz.model.domain.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String fullName;
    private String email;
    private String phone;
    private Date birthday;
    private String address;
    private String status;

    private String roleName;
}
