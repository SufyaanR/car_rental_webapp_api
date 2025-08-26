package za.ac.cput.service;

import za.ac.cput.domain.Booking;

public interface IBookingService extends IService<Booking, Long>{

    void cancelBooking(Long bookingId);
    void confirmBooking(Long bookingId);

}
