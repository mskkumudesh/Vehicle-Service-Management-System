package lk.ijse.vehicleservice.service.impl;

import lk.ijse.vehicleservice.dto.ServiceDTO;
import lk.ijse.vehicleservice.entity.Services;
import lk.ijse.vehicleservice.repository.ServiceRepository;
import lk.ijse.vehicleservice.service.ServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addService(ServiceDTO dto) {
        Services service = modelMapper.map(dto, Services.class);
        serviceRepository.save(service);
    }

    @Override
    public void updateService(ServiceDTO dto) {
        if (!serviceRepository.existsById(dto.getServiceId())) {
            throw new RuntimeException("Service not found");
        }

        Services service = modelMapper.map(dto, Services.class);
        serviceRepository.save(service);
    }

    @Override
    public void deleteService(String id) {
        int serviceId = Integer.parseInt(id);

        if (!serviceRepository.existsById(serviceId)) {
            throw new RuntimeException("Service not found");
        }

        serviceRepository.deleteById(serviceId);
    }

    @Override
    public ServiceDTO searchService(String id) {
        int serviceId = Integer.parseInt(id);

        Services service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

         return modelMapper.map(service, ServiceDTO.class);
    }

    @Override
    public List<ServiceDTO> getAllServices() {
        List<Services> services = serviceRepository.findAll();
                return services.stream().map(service -> modelMapper.map(service, ServiceDTO.class))
                .toList();
    }
}