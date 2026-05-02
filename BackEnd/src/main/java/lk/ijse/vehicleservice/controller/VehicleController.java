package lk.ijse.vehicleservice.controller;

import jakarta.validation.Valid;
import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.service.VehicleService;
import lk.ijse.vehicleservice.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<APIResponse<String>> addVehicle( Authentication authentication , @RequestBody @Valid VehicleDTO dto) {
        String email = authentication.getName();
        System.out.println(email);
        System.out.println(vehicleService.isExists(dto.getVehicleNumber()));

       if (vehicleService.isExists(dto.getVehicleNumber())) {
           System.out.println("Vehicle already exists");
           vehicleService.updateVehicle(email, dto);
           return ResponseEntity.ok(
                   new APIResponse<>(200, "Vehicle Updated Successfully", null)
           );
       }else {
           System.out.println(email);
           vehicleService.addVehicle(email, dto);
           return ResponseEntity.ok(
                   new APIResponse<>(200, "Vehicle Added Successfully", null)
           );
       }
    }

    @PostMapping("/admin/save")
    public ResponseEntity<APIResponse<String>> addVehicle(@RequestBody @Valid VehicleDTO dto) {
       if (vehicleService.isExists(dto.getVehicleNumber())) {
           System.out.println("Vehicle already exists");
           vehicleService.updateVehicle(dto);
           return new ResponseEntity<>(new APIResponse<>(200, "Vehicle Updated Successfully", null), HttpStatus.OK);
       }else  {
           vehicleService.addVehicle(dto);
           return new ResponseEntity<>(new APIResponse<>(200, "Vehicle Added Successfully", null), HttpStatus.OK);
       }
    }


    @PutMapping("/update")
    public ResponseEntity<APIResponse<String>> updateVehicle(Authentication authentication,@RequestBody @Valid VehicleDTO dto) {
        String email = authentication.getName();

        vehicleService.updateVehicle(email,dto);

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


    @DeleteMapping("/delete/{vehicleNumber}")
    public ResponseEntity<APIResponse<String>> deleteVehicle(@PathVariable String vehicleNumber) {

        vehicleService.deleteVehicle(vehicleNumber);

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