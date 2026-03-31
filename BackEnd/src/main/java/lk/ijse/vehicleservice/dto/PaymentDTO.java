package lk.ijse.vehicleservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDTO {
    @NotNull(message = "PaymentId is required")
    @Positive(message = "PaymentId must be greater than 0")
    private int paymentId;
    @NotNull(message = "Payment date is required")
    private Date paymentDate;
    @NotBlank(message = "Payment method is required")
    private String paymentMethod;
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount should be greater than 0")
    private double amount;
    @NotNull(message = "BookingId is required")
    @Positive(message = "BookingId must be greater than 0")
    private int bookingId;

}
