package lk.ijse.vehicleservice.service.impl;

import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.entity.User;
import lk.ijse.vehicleservice.entity.Vehicle;
import lk.ijse.vehicleservice.exception.custom.NotFoundException;
import lk.ijse.vehicleservice.repository.UserRepository;
import lk.ijse.vehicleservice.repository.VehicleRepository;
import lk.ijse.vehicleservice.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addVehicle(String email,VehicleDTO dto) {

        if (vehicleRepository.findByVehicleNumber((dto.getVehicleNumber())).isPresent()) {
            throw new RuntimeException("Vehicle already exists");
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(dto.getVehicleNumber());
        vehicle.setCategory(dto.getCategory());
        vehicle.setBrand(dto.getBrand());

        User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found in save"));
        vehicle.setUser(user);

        vehicleRepository.save(vehicle);
    }

    @Override
    public void updateVehicle(String email,VehicleDTO dto) {

        Vehicle vehicle = vehicleRepository.findByVehicleNumber(dto.getVehicleNumber())
                .orElseThrow(() -> new NotFoundException("vehicle not found"));
        vehicle.setVehicleNumber(dto.getVehicleNumber());
        vehicle.setCategory(dto.getCategory());
        vehicle.setBrand(dto.getBrand());
        User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found in update"));
        vehicle.setUser(user);
        vehicleRepository.save(vehicle);
    }

    @Override
    public VehicleDTO searchVehicle(int id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    @Override
    public void deleteVehicle(String vehicleNumber) {
        Vehicle vehicle = vehicleRepository.findByVehicleNumber(vehicleNumber)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));
        vehicleRepository.delete(vehicle);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream().map(vehicle -> modelMapper.map(vehicle,VehicleDTO.class)).toList();
    }

    @Override
    public List<VehicleDTO> getAllVehiclesByUser(String email) {

        List<Vehicle> vehicles = vehicleRepository
                .findAllByUser_Email(email);

        return vehicles.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .toList();
    }
    @Override
    public boolean isExists(String vehicleNumber) {
        return vehicleRepository.findByVehicleNumber(vehicleNumber).isPresent();
    }
}
