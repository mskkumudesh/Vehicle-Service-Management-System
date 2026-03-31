package lk.ijse.vehicleservice.controller;

import jakarta.validation.Valid;
import lk.ijse.vehicleservice.dto.PaymentDTO;
import lk.ijse.vehicleservice.service.PaymentService;
import lk.ijse.vehicleservice.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/save")
    public ResponseEntity<APIResponse<String>> addPayment(@Valid @RequestBody PaymentDTO dto) {
        paymentService.addPayment(dto);
        return new ResponseEntity<>(
                new APIResponse<>(201, "Payment Saved Successfully", null),
                HttpStatus.CREATED
        );
    }


    @PutMapping("/update")
    public ResponseEntity<APIResponse<String>> updatePayment(@Valid @RequestBody PaymentDTO dto) {
        paymentService.updatePayment(dto);
        return new ResponseEntity<>(
                new APIResponse<>(200, "Payment Updated Successfully", null),
                HttpStatus.OK
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<String>> deletePayment(@PathVariable int id) {
        paymentService.deletePayment(id);
        return new ResponseEntity<>(
                new APIResponse<>(200, "Payment Deleted Successfully", null),
                HttpStatus.OK
        );
    }


    @GetMapping("/search/{id}")
    public ResponseEntity<APIResponse<PaymentDTO>> getPayment(@PathVariable int id) {
        return new ResponseEntity<>(
                new APIResponse<>(200, "Success", paymentService.getPayment(id)),
                HttpStatus.OK
        );
    }


    @GetMapping("/getAll")
    public ResponseEntity<APIResponse<List<PaymentDTO>>> getAllPayments() {
        return new ResponseEntity<>(
                new APIResponse<>(200, "Success", paymentService.getAllPayments()),
                HttpStatus.OK
        );
    }
    @GetMapping("/getAllByUser")
    public ResponseEntity<APIResponse<List<PaymentDTO>>> getPaymentById(Authentication authentication ) {
        return new ResponseEntity<>(
                new APIResponse<>(200,"Success",paymentService.getAllByUser(authentication)),
                HttpStatus.OK
        );
    }
}