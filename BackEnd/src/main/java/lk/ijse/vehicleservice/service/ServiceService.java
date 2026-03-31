package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.ServiceDTO;

import java.util.List;

public interface ServiceService {
    void addService(ServiceDTO dto);
    void updateService(ServiceDTO dto);
    void deleteService(String id);
    ServiceDTO searchService(String id);
    List<ServiceDTO> getAllServices();
}
