package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Car;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByBookingId(Long bookingId);

    Booking findByCar(Car car);

    Booking findByCustomerId(Long customerId);

    Booking findByBookingDate(String bookingDate);

    Booking findByBookingStatus(String bookingStatus);


}
