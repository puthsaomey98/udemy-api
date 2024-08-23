package com.udemy.elearning.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
