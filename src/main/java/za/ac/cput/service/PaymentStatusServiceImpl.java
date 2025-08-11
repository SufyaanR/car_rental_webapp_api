package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.PaymentStatus;
import za.ac.cput.repository.PaymentStatusRepository;
import za.ac.cput.service.IPaymentStatusService;
import java.util.List;

@Service
public class PaymentStatusServiceImpl implements IPaymentStatusService {

    private final PaymentStatusRepository repository;

    @Autowired
    public PaymentStatusServiceImpl(PaymentStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PaymentStatus> getAll() {
        return repository.findAll();
    }

    @Override
    public PaymentStatus findByName(String name) {
        return repository.findByName(name.toUpperCase())
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid status: " + name));
    }
}