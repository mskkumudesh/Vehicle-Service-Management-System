package lk.ijse.vehicleservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @Column(nullable = false)
    private Date bookingDate;

    @Column(nullable = false)
    private Time bookingTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;

    @ManyToMany
    @JoinTable(
            name = "booking_service",
            joinColumns = @JoinColumn(name = "bookingId"),
            inverseJoinColumns = @JoinColumn(name = "serviceId")
    )
    private List<Services> serviceDTOs;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Payment payment;
    
}
