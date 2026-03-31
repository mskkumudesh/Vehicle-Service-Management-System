package lk.ijse.vehicleservice.repository;

import lk.ijse.vehicleservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findAllByBooking_Vehicle_User_UserId(Integer id);

    List<Payment> findAllByBooking_Vehicle_User_Email(String email);
}
