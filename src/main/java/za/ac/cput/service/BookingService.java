package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Booking;
import za.ac.cput.repository.BookingRepository;
import java.util.List;

@Service
@Transactional
public class BookingService implements IBookingService {

    private final BookingRepository BookingRepo;

    @Autowired
    public BookingService(BookingRepository BookingRepo) {
        this.BookingRepo = BookingRepo;
    }

    @Override
    public Booking save(Booking booking) {
        return BookingRepo.save(booking);
    }

    @Override
    public Booking findById(Long id) {
        return BookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public List<Booking> findAll() {
        return BookingRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        BookingRepo.deleteById(id);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Booking booking = findById(bookingId);
        booking.cancelBooking();
        BookingRepo.save(booking);
    }

    public Booking update(Long id, Booking updates) {
        Booking existing = findById(id);

        Booking updated = new Booking.Builder()
                .setBookingId(existing.getBookingId())
                .setStartDate(updates.getStartDate() != null ? updates.getStartDate() : existing.getStartDate())
                .setEndDate(updates.getEndDate() != null ? updates.getEndDate() : existing.getEndDate())
                .setTotalPrice(updates.getTotalPrice() != null ? updates.getTotalPrice() : existing.getTotalPrice())
                .setBookingStatus(
                        updates.getBookingStatus() != null ? updates.getBookingStatus() : existing.getBookingStatus())
                .setUser(updates.getUser() != null ? updates.getUser() : existing.getUser())
                .setCar(updates.getCar() != null ? updates.getCar() : existing.getCar())
                .build();

        return BookingRepo.save(updated);
    }

    @Override
    public void confirmBooking(Long bookingId) {
        Booking booking = findById(bookingId);
        booking.confirmBooking();
        BookingRepo.save(booking);
    }

    public List<Booking> findBookingsByProUserId(Long proUserId) {
        return BookingRepo.findByCarProUserUserId(proUserId);
    }

    public List<Booking> findBookingsByBusinessUserId(Long businessUserId) {
        return BookingRepo.findByCarBusinessUserUserId(businessUserId);
    }
}