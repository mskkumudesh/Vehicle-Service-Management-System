package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    void addBooking(BookingDTO dto);
    void updateBooking(BookingDTO dto);
    void deleteBooking(String input);
    List<BookingDTO> searchBooking(String input);
    List<BookingDTO> getAllBookings();
    List<BookingDTO> getAllBookingsByUser(String input);
    List<BookingDTO> getAllBookingsByVehicle(String input);
}
