package lk.ijse.vehicleservice.dto;

import jakarta.validation.constraints.*;
import lk.ijse.vehicleservice.entity.Category;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VehicleDTO {
    @Positive(message = "VehicleId must be greater than 0")
    private Integer vehicleId;
    @Positive(message = "UserId must be greater than 0")
    private Integer userId;
    @NotBlank(message = "Vehicle number is required")
    @Pattern(regexp = "^[A-Z]{2,3}\\d{4}$",message = "Invalid vehicle number")
    private String vehicleNumber;
    @NotNull(message = "Category is required")
    private Category category;
    @NotBlank(message = "Brand is required")
    @Size(min = 2,max = 10,message = "Not a valid brand")
    private String brand;
}