package lk.ijse.vehicleservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;

    @Column(nullable = false)
    private String serviceName;

   @Column(nullable = false)
   private Double price;

    @ManyToMany(mappedBy = "serviceDTOs")
    private List<Booking> bookings;
}