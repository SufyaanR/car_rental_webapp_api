package za.ac.cput.factory;

import java.math.BigDecimal;
import java.time.LocalDate;
import za.ac.cput.domain.BasicUser;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.BookingStatus;
import za.ac.cput.domain.Car;

public class BookingFactory {

    public static Booking create(LocalDate startDate, LocalDate endDate, BigDecimal totalPrice, BookingStatus bookingStatus, BasicUser user, Car car) {
        return new Booking.Builder()
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setTotalPrice(totalPrice)
                .setBookingStatus(bookingStatus)
                .setUser(user)
                .setCar(car)
                .build();
    }
}