package lk.ijse.vehicleservice.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lk.ijse.vehicleservice.entity.Booking;
import lk.ijse.vehicleservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    Booking findByVehicle_VehicleNumber(String vehicleNumber);

    List<Booking> findAllByVehicle_User_UserIdOrVehicle_User_Email(Integer userId ,String input);

    List<Booking> findAllByVehicle_Category(Category category);

    boolean existsByVehicle_VehicleNumberOrVehicle_VehicleId(String vehicleNumber , int vehicleId);

    List<Booking> findAllByVehicle_VehicleNumberOrVehicle_VehicleId( String vehicleNumber, int vehicleId);

    boolean existsByVehicle_VehicleNumberOrBookingDate(String vehicle_vehicleNumber, Date bookingDate);

    Booking findByVehicle_VehicleNumberOrVehicle_VehicleId(@NotBlank(message = "Vehicle number is required") String vehicleNumber, @Positive(message = "VehicleId must be greater than 0") @NotBlank(message = "VehicleId is required") int vehicleId);

}