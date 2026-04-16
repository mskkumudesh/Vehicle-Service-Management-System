package lk.ijse.vehicleservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lk.ijse.vehicleservice.entity.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    @Positive(message = "User Id must be greater than 0")
    private Integer id;
    @NotBlank(message = "Name is required")
    @Size(min = 3,max = 50,message = "Not a valid name")
    private String name;
    @NotBlank(message = "Email is required")
    @Size(min = 10,max = 50,message = "Not a valid email")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Username is required")
    @Size(min = 3,max = 20,message = "Not a valid username")
    private String username;
    @NotBlank(message = "Password is required")
    @Size(min = 6,message = "Password must be at least 6 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotNull(message = "Role is required")
    private Role role;
}
