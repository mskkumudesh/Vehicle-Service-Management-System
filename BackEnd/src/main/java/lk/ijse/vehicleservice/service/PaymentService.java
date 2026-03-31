package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.PaymentDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PaymentService {
    public void addPayment(PaymentDTO dto);
    public void updatePayment(PaymentDTO dto);
    public void deletePayment(int id);
    public PaymentDTO getPayment(int id);
    public List<PaymentDTO> getAllPayments();

    List<PaymentDTO> getAllByUser(Authentication authentication);
}
