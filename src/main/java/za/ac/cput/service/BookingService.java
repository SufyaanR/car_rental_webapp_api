package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Booking;
import za.ac.cput.repository.BookingRepository;

import java.util.List;

@Service
public class BookingService implements IBookingService {


    private IBookingService service;
    @Autowired
    private BookingRepository repository;

    @Override
    public Booking create(Booking booking) {
        return this.repository.save(booking);
    }

    @Override
    public Booking read(Long aLong) {
        return this.repository.findById(aLong).orElse(null);
    }

    @Override
    public Booking update(Booking booking) {
        return this.repository.save(booking);
    }

    @Override
    public boolean delete(Long aLong) {
        this.repository.deleteById(aLong);
        return true;
    }

    @Override
    public List<Booking> getAll() {
        return this.repository.findAll();
    }

}
