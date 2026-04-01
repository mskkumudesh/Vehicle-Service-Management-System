package lk.ijse.vehicleservice.controller;

import jakarta.validation.Valid;
import lk.ijse.vehicleservice.dto.BookingDTO;
import lk.ijse.vehicleservice.service.BookingService;
import lk.ijse.vehicleservice.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/booking")
@RestController
@CrossOrigin
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @PostMapping("/save")
    public ResponseEntity<APIResponse<String>> addBooking(@RequestBody @Valid BookingDTO bookingDTO) {
          bookingService.addBooking(bookingDTO);
        return new ResponseEntity<>(new APIResponse<>(201,"Booking saved",null), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<APIResponse<String>> updateBooking(@RequestBody @Valid BookingDTO bookingDTO) {
        bookingService.updateBooking(bookingDTO);
        return new ResponseEntity<>(new APIResponse<>(200,"Booking updated",null), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{input}")
    public ResponseEntity<APIResponse<String>> deleteBooking(@PathVariable String input) {
        bookingService.deleteBooking(input);
        return new ResponseEntity<>(new APIResponse<>(200,"Booking deleted",null), HttpStatus.OK);
    }
    @GetMapping("/search/{input}")
    public ResponseEntity<APIResponse<List<BookingDTO>>> searchBooking(@PathVariable String input) {
       List<BookingDTO> bookingDTOs = bookingService.searchBooking(input);
        return new ResponseEntity<>(new APIResponse<>(200,"Booking searched",bookingDTOs), HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public  ResponseEntity<APIResponse<List<BookingDTO>>> getAllBookings(){
        List<BookingDTO> bookingDTOs = bookingService.getAllBookings();
        return new ResponseEntity<>(new APIResponse<>(200,"All bookings",bookingDTOs), HttpStatus.OK);
    }
    @GetMapping("/getAllByUser")
    public  ResponseEntity<APIResponse<List<BookingDTO>>> getAllBookingsByUser(Authentication  authentication){
        String email = authentication.getName();
        List<BookingDTO> bookingDTOS = bookingService.getAllBookingsByUser(email);
        return new ResponseEntity<>(new APIResponse<>(200,"All bookings",bookingDTOS), HttpStatus.OK);
    }
}
