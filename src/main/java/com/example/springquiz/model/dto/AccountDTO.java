package com.example.springquiz.model.dto;

import com.example.springquiz.model.domain.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    @Pattern(regexp = "^[a-zA-Z0-9._]{3,16}$",message = "INVALID_USERNAME")
    @Schema(description = "The name of the user", example = "john_doe", defaultValue = "john_doe")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9._]{3,16}$",message = "INVALID_PASSWORD")
    @Schema(description = "The password of the user", example = "123456", defaultValue = "123456")
    private String password;

    @Pattern(regexp = "^[A-Za-z\\s]{3,16}$",message = "INVALID_FULLNAME")
    @Schema(description = "The fullName of the user", example = "John Doe", defaultValue = "John Doe")
    private String fullName;

    @Pattern(regexp = "^[a-z]+\\w+@([\\w-]+\\.)+[\\w-]{2,4}$",message = "INVALID_EMAIL")
    @Schema(description = "The email of the user", example = "johndoe123@gmail.com", defaultValue = "johndoe123@gmail.com")
    private String email;

    @Pattern(regexp = "^\\d{8,12}$", message = "INVALID_PHONE_NUMBER")
    @Schema(description = "The phone of the user", example = "0988454005", defaultValue = "0988454005")
    private String phone;

    @Schema(description = "The birthday of the user", example = "1980-01-01", defaultValue = "1980-01-01")
    private Date birthday;

    @Schema(description = "The address of the user", example = "123 Admin St", defaultValue = "123 Admin St")
    private String address;

    @Schema(description = "The status of the user", example = "Active", defaultValue = "Active")
    private String status;

    @Schema(description = "The role name of the user", example = "User", defaultValue = "User")
    private String roleName;
}
