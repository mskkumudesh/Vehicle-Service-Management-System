package lk.ijse.vehicleservice.repository;

import lk.ijse.vehicleservice.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Services,Integer> {
    List<Services> findAllByBookings_BookingId(int id);
}
