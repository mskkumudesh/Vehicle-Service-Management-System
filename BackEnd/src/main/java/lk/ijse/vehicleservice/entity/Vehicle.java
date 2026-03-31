package lk.ijse.vehicleservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleId;
    @Column(nullable = false, unique = true)
    private String vehicleNumber;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(nullable = false)
    private String brand;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List <Booking> bookings;

}
