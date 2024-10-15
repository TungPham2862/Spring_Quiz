package com.example.springquiz.model.dto;

import com.example.springquiz.Enum.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private RoleName roleName;
    private String status;
}
