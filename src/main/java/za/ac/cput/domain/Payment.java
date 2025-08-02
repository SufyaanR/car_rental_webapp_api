package za.ac.cput.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private int cardNumber;
    private String nameOfCardHolder;
    private LocalDate expiryDate;
    private String cvv;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private LocalTime paymentTime;
    //private PaymentStatus paymentStatus;
    //private User user;
    //private Booking booking;
    //private RentalProvider rentalProvider;

    public Payment() {}

    public Payment(PaymentBuilder builder) {
        this.paymentId = builder.paymentId;
        this.cardNumber = builder.cardNumber;
        this.nameOfCardHolder = builder.nameOfCardHolder;
        this.expiryDate = builder.expiryDate;
        this.cvv = builder.cvv;
        this.amount = builder.amount;
        this.paymentDate = builder.paymentDate;
        this.paymentTime = builder.paymentTime;
      //  this.userId = builder.userId;
       // this.bookingId = builder.bookingId;
       // this.rentalProviderId = builder.rentalProviderId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getNameOfCardHolder() {
        return nameOfCardHolder;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalTime getPaymentTime() {
        return paymentTime;
    }

   // public User getUser() {
   //     return user;
   // }

    //public Booking getBooking() {
    //    return booking;
    //}

   // public RentalProvider getRentalProvider() {
    //    return rentalProvider;
   // }

    public static class PaymentBuilder {

        private Long paymentId;
        private int cardNumber;
        private String nameOfCardHolder;
        private LocalDate expiryDate;
        private String cvv;
        private BigDecimal amount;
        private LocalDate paymentDate;
        private LocalTime paymentTime;
        //private PaymentStatus paymentStatus;
        //private User user;
        //private Booking booking;
        //private RentalProvider rentalProvider;

        public PaymentBuilder setPaymentId(Long paymentId) {
            this.paymentId = paymentId;
            return this;
        }
        public PaymentBuilder setCardNumber(int cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public PaymentBuilder setNameOfCardHolder(String nameOfCardHolder) {
            this.nameOfCardHolder = nameOfCardHolder;
            return this;
        }

        public PaymentBuilder setExpiryDate(LocalDate expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public PaymentBuilder setCvv(String cvv) {
            this.cvv = cvv;
            return this;
        }

        public PaymentBuilder setPaymentDate(LocalDate paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public PaymentBuilder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public PaymentBuilder setPaymentTime(LocalTime paymentTime) {
            this.paymentTime = paymentTime;
            return this;
        }

       // public PaymentBuilder setUser(User user) {
        //    this.user = user;
        //    return this;
        //}

       // public PaymentBuilder setBookingId(Booking booking) {
         //   this.booking = booking;
         //   return this;
       // }

       // public PaymentBuilder setRentalProviderId(RentalProvider rentalProvider) {
         //   this.rentalProvider = rentalProvider;
          //  return this;
       // }

        public Payment build() {
            return new Payment(this);
        }
    }


    
}
