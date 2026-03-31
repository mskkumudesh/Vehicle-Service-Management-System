package lk.ijse.vehicleservice.service.impl;

import lk.ijse.vehicleservice.dto.BookingDTO;
import lk.ijse.vehicleservice.entity.Booking;
import lk.ijse.vehicleservice.repository.BookingRepository;
import lk.ijse.vehicleservice.service.BookingService;
import org.hibernate.validator.internal.constraintvalidators.bv.time.future.FutureValidatorForInstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void addBooking(BookingDTO dto) {

        if (bookingRepository.existsByVehicle_VehicleNumberOrBookingDate(
                dto.getVehicleNumber(), dto.getBookingDate())) {
            throw new RuntimeException("Vehicle already has a booking on given date");
        }

        Booking booking = modelMapper.map(dto, Booking.class);
        bookingRepository.save(booking);
    }

    @Override
    public void updateBooking(BookingDTO dto) {

        Booking booking = bookingRepository
                .findByVehicle_VehicleNumberOrVehicle_VehicleId(
                        dto.getVehicleNumber(), dto.getVehicleId());

        if (booking == null) {
            throw new RuntimeException("Booking not found");
        }
        if (booking.getBookingDate().toLocalDate().isBefore(LocalDate.now())){
            throw new RuntimeException("Booking date should be today or future");
        }

        bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(String input) {
//        int vehicleId = parseUserId(input);

//        Booking booking = bookingRepository
//                .findAllByVehicle_VehicleNumberOrVehicle_VehicleId(input,vehicleId);

//        if (booking == null) {
//            throw new RuntimeException("Booking not found");
//        }
//
//        bookingRepository.delete(booking);
    }

    @Override
    public List<BookingDTO> searchBooking(String input) {
        int vehicleId = parseUserId(input);


        List<Booking> bookings = bookingRepository
                .findAllByVehicle_VehicleNumberOrVehicle_VehicleId(input, vehicleId);

        if (bookings.isEmpty()) {
            throw new RuntimeException("Booking not found");
        }
        return  bookings
                .stream()
                .map(b -> modelMapper.map(b, BookingDTO.class))
                .toList();
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(b -> modelMapper.map(b, BookingDTO.class))
                .toList();
    }


    @Override
    public List<BookingDTO> getAllBookingsByUser(String input) {

        Integer userId = parseUserId(input);

        return bookingRepository
                .findAllByVehicle_User_UserIdOrVehicle_User_Email(userId, input)
                .stream()
                .map(b -> modelMapper.map(b, BookingDTO.class))
                .toList();
    }


    @Override
    public List<BookingDTO> getAllBookingsByVehicle(String input) {

        int vehicleId = parseUserId(input);
       List<Booking> bookings = bookingRepository.findAllByVehicle_VehicleNumberOrVehicle_VehicleId(input, vehicleId);

        return   bookings
                .stream()
                .map(b -> modelMapper.map(b, BookingDTO.class))
                .toList();
    }


    private int parseUserId(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}