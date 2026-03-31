package lk.ijse.vehicleservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lk.ijse.vehicleservice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 3,max = 20,message ="Not a valid name")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Username is required")
    @Size(min = 3,max = 10,message = "Not a valid Username")
    private String username;
    @NotBlank(message = "Password is required")
    @Size(min = 5,message = "Password should be more than 5 characters")
    private String password;
}
