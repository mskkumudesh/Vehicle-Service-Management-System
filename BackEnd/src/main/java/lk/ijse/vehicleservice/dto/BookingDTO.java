package lk.ijse.vehicleservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lk.ijse.vehicleservice.entity.Status;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookingDTO {
    @Positive(message = "Booking Id must be greater than 0")
    private Integer bookingId;
    @NotBlank(message = "Vehicle number is required")
    private String vehicleNumber;
    private List<ServiceDTO> serviceDTOs;

    @NotNull(message = "Booking date is required")
    @FutureOrPresent(message = "Booking date must be today or in the future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bookingDate;
    @NotNull(message = "Booking time is required")
    private Time bookingTime;
    @NotNull(message = "Booking status is required")
    private Status status;
    private Double totalAmount;
}
