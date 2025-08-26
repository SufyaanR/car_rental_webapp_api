package za.ac.cput.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @ManyToOne
    private BasicUser user;

    @ManyToOne
    private Car car;

    protected Booking(){
        
    }

    private Booking(Builder builder) {
        this.bookingId = builder.bookingId;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.totalPrice = builder.totalPrice;
        this.bookingStatus = builder.bookingStatus;
        this.user = builder.user;
        this.car = builder.car;
    }

    public Long getBookingId() {
        return bookingId;
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

    public BasicUser getUser() {
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
            "bookingId=" + getBookingId() +
            ", startDate=" + getStartDate() +
            ", endDate=" + getEndDate() +
            ", totalPrice=" + getTotalPrice() +
            ", bookingStatus=" + getBookingStatus() +
            ", userId=" + (getUser() != null ? getUser().getUserId() : "null") +
            ", carId=" + (getCar() != null ? getCar().getCarId() : "null") +
            '}';
}

    public static class Builder {
        private Long bookingId;
        private LocalDate startDate;
        private LocalDate endDate;
        private BigDecimal totalPrice;
        private BookingStatus bookingStatus;
        private BasicUser user;
        private Car car;

        public Builder setBookingId(Long bookingId) {
            this.bookingId = bookingId;
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

        public Builder setUser(BasicUser user) {
            this.user = user;
            return this;
        }

        public Builder setCar(Car car) {
            this.car = car;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}