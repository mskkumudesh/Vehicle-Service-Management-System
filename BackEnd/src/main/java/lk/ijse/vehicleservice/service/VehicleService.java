package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.UserDTO;
import lk.ijse.vehicleservice.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {

    void addVehicle(String email,VehicleDTO dto);

    void addVehicle(VehicleDTO dto);

    void updateVehicle(String email, VehicleDTO dto);

    void updateVehicle(VehicleDTO dto);

    VehicleDTO searchVehicle(int id);

    void deleteVehicle(String vehicleNumber);

    List<VehicleDTO> getAllVehicles();

    List<VehicleDTO> getAllVehiclesByUser(String email);

    boolean isExists(String vehicleNumber);
}