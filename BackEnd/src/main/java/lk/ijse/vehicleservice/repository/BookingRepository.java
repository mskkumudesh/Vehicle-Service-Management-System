package lk.ijse.vehicleservice.repository;

import lk.ijse.vehicleservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    Booking findByVehicle_VehicleNumber(String vehicleNumber);

    List<Booking> findAllByVehicle_VehicleNumberOrVehicle_VehicleId( String vehicleNumber, int vehicleId);

    List<Booking> findAllByVehicle_VehicleNumber(String vehicleNumber);

    List<Booking> findAllByVehicle_User_Email(String email);

    boolean existsByBookingDateAndVehicle_VehicleNumber(Date bookingDate, String vehicleVehicleNumber);
}