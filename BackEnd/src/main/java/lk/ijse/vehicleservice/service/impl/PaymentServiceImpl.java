package lk.ijse.vehicleservice.service.impl;

import lk.ijse.vehicleservice.dto.PaymentDTO;
import lk.ijse.vehicleservice.entity.Payment;
import lk.ijse.vehicleservice.repository.PaymentRepository;
import lk.ijse.vehicleservice.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addPayment(PaymentDTO dto) {
        Payment payment = modelMapper.map(dto, Payment.class);
        paymentRepository.save(payment);
    }

    @Override
    public void updatePayment(PaymentDTO dto) {
        if (!paymentRepository.existsById(dto.getPaymentId())) {
            throw new RuntimeException("Payment not found");
        }
        Payment payment = modelMapper.map(dto, Payment.class);
        paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(int id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment not found");
        }
        paymentRepository.deleteById(id);
    }

    @Override
    public PaymentDTO getPayment(int id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return modelMapper.map(payment, PaymentDTO.class);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .collect(Collectors.toList());
    }
   @Override
   public List<PaymentDTO> getAllByUser(Authentication authentication){
        String email = authentication.getName();
        return paymentRepository.findAllByBooking_Vehicle_User_Email(email)
                .stream()
                .map(payment -> modelMapper.map(payment,PaymentDTO.class))
                .collect(Collectors.toList());
    }
}