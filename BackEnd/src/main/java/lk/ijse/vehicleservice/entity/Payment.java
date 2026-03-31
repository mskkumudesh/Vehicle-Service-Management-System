package lk.ijse.vehicleservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    @NotNull(message = "Payment date is required")
    private Date paymentDate;
    @NotBlank(message = "Payment method is required")
    private String paymentMethod;
    @NotNull(message = "Amount is required")
    private double amount;
    @OneToOne
    @JoinColumn(name = "bookingID",nullable = false,unique = true)
    private Booking booking;

}
