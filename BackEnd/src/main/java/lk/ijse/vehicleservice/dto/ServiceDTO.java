package lk.ijse.vehicleservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ServiceDTO {
    @NotNull(message = "ServiceId is required")
    @Positive(message = "ServiceId must be greater than 0")
    private int serviceId;
    @NotBlank(message = "Service name is required")
    @Size(min = 4,max = 20,message = "Not a valid service name")
    private String serviceName;
    @NotBlank(message = "Price is required")
    @Positive(message = "Price must be greater than 0 ")
    private Double price;
}
