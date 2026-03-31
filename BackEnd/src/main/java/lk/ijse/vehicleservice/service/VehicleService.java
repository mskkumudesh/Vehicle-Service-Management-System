package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.UserDTO;
import lk.ijse.vehicleservice.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {

    void addVehicle(VehicleDTO dto);

    void updateVehicle(VehicleDTO dto);

    VehicleDTO searchVehicle(int id);

    void deleteVehicle(int id);

    List<VehicleDTO> getAllVehicles();

    List<VehicleDTO> getAllVehiclesByUser(String input);
}