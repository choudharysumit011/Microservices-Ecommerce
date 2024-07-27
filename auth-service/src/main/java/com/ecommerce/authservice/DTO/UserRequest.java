package com.ecommerce.authservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotNull(message = "Username must not be null")
    @Size(min = 3, max = 20, message = "Username must be of 3 to 20 char")
    private String username;
    @NotNull(message = "Password must not be null")
    @Size(min = 8, max = 20, message = "Password must be of 8 to 20 char")
    private String password;
}
