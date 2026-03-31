package lk.ijse.vehicleservice.controller;

import jakarta.validation.Valid;
import lk.ijse.vehicleservice.dto.ServiceDTO;
import lk.ijse.vehicleservice.service.ServiceService;
import lk.ijse.vehicleservice.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/service")
@CrossOrigin
public class ServiceController {

    @Autowired
    private ServiceService serviceService;


    @PostMapping("/add")
    public ResponseEntity<APIResponse<String>> addService(@Valid @RequestBody ServiceDTO dto) {
        serviceService.addService(dto);
        return new ResponseEntity<>(
                new APIResponse<>(201, "Service Added Successfully", null),
                HttpStatus.CREATED
        );
    }


    @PutMapping("/update")
    public ResponseEntity<APIResponse<String>> updateService(@Valid @RequestBody ServiceDTO dto) {
        serviceService.updateService(dto);
        return new ResponseEntity<>(
                new APIResponse<>(200, "Service Updated Successfully", null),
                HttpStatus.OK
        );
    }


    @DeleteMapping("delete//{id}")
    public ResponseEntity<APIResponse<String>> deleteService(@PathVariable String id) {
        serviceService.deleteService(id);
        return new ResponseEntity<>(
                new APIResponse<>(200, "Service Deleted Successfully", null),
                HttpStatus.OK
        );
    }


    @GetMapping("search/{id}")
    public ResponseEntity<APIResponse<ServiceDTO>> searchService(@PathVariable String id) {
        ServiceDTO service = serviceService.searchService(id);
        return new ResponseEntity<>(
                new APIResponse<>(200, "Service Found", service),
                HttpStatus.OK
        );
    }


    @GetMapping("/getAll")
    public ResponseEntity<APIResponse<List<ServiceDTO>>> getAllServices() {
                 List<ServiceDTO> serviceDTOS = serviceService.getAllServices();
        return new ResponseEntity<>(
                new APIResponse<>(200, "Success",serviceDTOS ),
                HttpStatus.OK
        );
    }
}