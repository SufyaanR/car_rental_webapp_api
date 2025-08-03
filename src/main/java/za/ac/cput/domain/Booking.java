package za.ac.cput.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingid;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalPrice;
    private BookingStatus bookingStatus;
    private User user;
    private Car car;


    protected Booking() {

    }

    private Booking(Booking.Builder builder) {
        this.bookingid = builder.bookingid;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.totalPrice = builder.totalPrice;
        this.bookingStatus = builder.bookingStatus;
        this.user = builder.user;
        this.car = builder.car;

    }

    public Long getBookingid() {
        return bookingid;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public void cancelBooking() {
        this.bookingStatus = BookingStatus.CANCELLED;
    }

    public void confirmBooking() {
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingid=" + bookingid +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalPrice=" + totalPrice +
                ", bookingStatus=" + bookingStatus +
                ", user=" + user +
                ", car=" + car +
                '}';
    }
    public static class Builder {

        private Long bookingid;
        private LocalDate startDate;
        private LocalDate endDate;
        private BigDecimal totalPrice;
        private BookingStatus bookingStatus;
        private User user;
        private Car car;

        public Builder setBookingid(Long bookingid) {
            this.bookingid = bookingid;
            return this;
        }

        public Builder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setTotalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder setBookingStatus(BookingStatus bookingStatus) {
            this.bookingStatus = bookingStatus;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setCar(Car car) {
            this.car = car;
            return this;
        }

        public Booking.Builder copy(Booking booking) {

            this.bookingid = booking.bookingid;
            this.startDate = booking.startDate;
            this.endDate = booking.endDate;
            this.totalPrice = booking.totalPrice;
            this.bookingStatus = booking.bookingStatus;
            this.user = booking.user;
            this.car = booking.car;
            return this;
        }

        public Booking build() {return new Booking(this);
        }

    }
    }






