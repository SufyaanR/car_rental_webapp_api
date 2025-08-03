package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.BookingStatus;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingFactory {
    public static Booking createBooking(  Long bookingid, LocalDate startDate, LocalDate endDate,
     BigDecimal totalPrice, BookingStatus bookingStatus, User user, Car car){

        return new Booking.Builder()
                .setBookingid(bookingid)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setTotalPrice(totalPrice)
                .setBookingStatus(bookingStatus)
                .setUser(user)
                .setCar(car)
                .build();
    }
}
