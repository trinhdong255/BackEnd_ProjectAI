package com.example.eccommerce_ai.dto;


import com.example.eccommerce_ai.model.Role;
import com.example.eccommerce_ai.model.User;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;
    private String email;
    private String username;
    private String roleName; // Chỉ trả về tên Role, không trả cả Entity
    private boolean activated;
    private LocalDateTime createdAt;


}
