package lk.ijse.vehicleservice.controller;

import jakarta.validation.Valid;
import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.service.VehicleService;
import lk.ijse.vehicleservice.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;


    @PostMapping("/save")
    public ResponseEntity<APIResponse<String>> addVehicle(@RequestBody @Valid VehicleDTO dto) {

        vehicleService.addVehicle(dto);

        return ResponseEntity.ok(
                new APIResponse<>(200, "Vehicle Added Successfully", null)
        );
    }


    @PutMapping("/update")
    public ResponseEntity<APIResponse<String>> updateVehicle(@RequestBody @Valid VehicleDTO dto) {

        vehicleService.updateVehicle(dto);

        return ResponseEntity.ok(
                new APIResponse<>(200, "Vehicle Updated Successfully", null)
        );
    }


    @GetMapping("/search/{id}")
    public ResponseEntity<APIResponse<VehicleDTO>> searchVehicle(@PathVariable int id) {

        VehicleDTO vehicle = vehicleService.searchVehicle(id);

        return ResponseEntity.ok(
                new APIResponse<>(200, "Vehicle Found", vehicle)
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<String>> deleteVehicle(@PathVariable int id) {

        vehicleService.deleteVehicle(id);

        return ResponseEntity.ok(
                new APIResponse<>(200, "Vehicle Deleted Successfully", null)
        );
    }
    @GetMapping("/getAll")
    public ResponseEntity<APIResponse<List<VehicleDTO>>> getAllVehicles() {
        List<VehicleDTO> vehicleDTOS = vehicleService.getAllVehicles();
        return ResponseEntity.ok(
                new APIResponse<>(200,"success",vehicleDTOS)
        );
    }

    @GetMapping("/getAllByUser")
    public ResponseEntity<APIResponse<List<VehicleDTO>>> getVehiclesByUser(Authentication authentication) {
        String email = authentication.getName();

        List<VehicleDTO> vehicles = vehicleService.getAllVehiclesByUser(email);

        return ResponseEntity.ok(
                new APIResponse<>(200, "Vehicle List", vehicles)
        );
    }
}