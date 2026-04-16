package lk.ijse.vehicleservice.service.impl;

import lk.ijse.vehicleservice.dto.BookingDTO;
import lk.ijse.vehicleservice.entity.Booking;
import lk.ijse.vehicleservice.entity.Services;
import lk.ijse.vehicleservice.entity.Vehicle;
import lk.ijse.vehicleservice.repository.BookingRepository;
import lk.ijse.vehicleservice.repository.ServiceRepository;
import lk.ijse.vehicleservice.repository.VehicleRepository;
import lk.ijse.vehicleservice.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addBooking(BookingDTO dto) {
      if (bookingRepository.existsByBookingDateAndVehicle_VehicleNumber(dto.getBookingDate(), dto.getVehicleNumber())) {
          throw new RuntimeException("booking already exists on given date");
      }
        Booking booking = new Booking();
        booking.setBookingDate(dto.getBookingDate());
        booking.setBookingTime(dto.getBookingTime());
        booking.setStatus(dto.getStatus());


        Vehicle vehicle = vehicleRepository.findByVehicleNumber(dto.getVehicleNumber())
                .orElseThrow(() -> new RuntimeException("Vehicle " + dto.getVehicleNumber() + " not found"));
        booking.setVehicle(vehicle);


        List<Services> services = dto.getServiceDTOs().stream()
                .map(b -> serviceRepository.findById(b.getServiceId())
                        .orElseThrow(() -> new RuntimeException("Service not found")))
                .toList();
        booking.setServiceDTOs(services);


        double total = services.stream().mapToDouble(Services::getPrice).sum();
        booking.setTotalAmount(total);

        bookingRepository.save(booking);
    }
    @Override
    public void updateBooking(BookingDTO dto) {

        Booking booking = bookingRepository.findById(dto.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));


        if (dto.getBookingDate().toLocalDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Booking date should be today or in the future");
        }


        boolean exists = bookingRepository
                .existsByBookingDateAndVehicle_VehicleNumber(
                        dto.getBookingDate(), dto.getVehicleNumber());

//        if (exists && !booking.getBookingId().equals(dto.getBookingId())) {
//            throw new RuntimeException("booking already exists on given date");
//        }


        booking.setBookingDate(dto.getBookingDate());
        booking.setBookingTime(dto.getBookingTime());
        booking.setStatus(dto.getStatus());


        Vehicle vehicle = vehicleRepository.findByVehicleNumber(dto.getVehicleNumber())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        booking.setVehicle(vehicle);


        if (dto.getServiceDTOs() == null || dto.getServiceDTOs().isEmpty()) {
            throw new RuntimeException("Select at least one service");
        }

        List<Services> services = dto.getServiceDTOs().stream()
                .map(s -> serviceRepository.findById(s.getServiceId())
                        .orElseThrow(() -> new RuntimeException("Service not found")))
                .collect(Collectors.toList());

        booking.setServiceDTOs(services);


        double total = services.stream().mapToDouble(Services::getPrice).sum();
        booking.setTotalAmount(total);

        bookingRepository.save(booking);
    }
@Override
        public void deleteBooking(String id) {
        Booking booking = bookingRepository
                .findById(Integer.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        bookingRepository.delete(booking);
    }

    @Override
    public List<BookingDTO> searchBooking(String vehicleNumber) {
        List<Booking> bookings = bookingRepository.findAllByVehicle_VehicleNumber(vehicleNumber);

        if (bookings.isEmpty()) {
            throw new RuntimeException("Booking not found");
        }

        return bookings.stream()
                .map(b -> {
                    BookingDTO dto = modelMapper.map(b, BookingDTO.class);
                    double total = b.getServiceDTOs() != null
                            ? b.getServiceDTOs().stream().mapToDouble(Services::getPrice).sum()
                            : 0;
                    dto.setTotalAmount(total);
                    return dto;
                })
                .toList();
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(b -> {
                    BookingDTO dto = modelMapper.map(b, BookingDTO.class);
                    double total = b.getServiceDTOs() != null
                            ? b.getServiceDTOs().stream().mapToDouble(Services::getPrice).sum()
                            : 0;
                    dto.setTotalAmount(total);
                    return dto;
                })
                .toList();
    }

    @Override
    public List<BookingDTO> getAllBookingsByUser(String email) {
        return bookingRepository.findAllByVehicle_User_Email(email)
                .stream()
                .map(b -> {
                    BookingDTO dto = modelMapper.map(b, BookingDTO.class);
                    double total = b.getServiceDTOs() != null
                            ? b.getServiceDTOs().stream().mapToDouble(Services::getPrice).sum()
                            : 0;
                    dto.setTotalAmount(total);
                    return dto;
                })
                .toList();
    }

    @Override
    public boolean isExists(int id) {
        return bookingRepository.findById(id).isPresent();
    }
}