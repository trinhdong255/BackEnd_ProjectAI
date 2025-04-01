package com.example.eccommerce_ai.dto;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class UserTokenDTO {

    private String refreshToken;
    private LocalDateTime expiresAt;

}
