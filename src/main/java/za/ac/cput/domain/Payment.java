package za.ac.cput.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private int cardNumber;
    private String nameOfCardHolder;
    private LocalDate expiryDate;
    private String ccv;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private LocalTime paymentTime;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "basic_user_id", nullable = false)
    private BasicUser user;

    @ManyToOne
    @JoinColumn(name = "pro_user_id", nullable = true)
    private ProUser proUser;

    @ManyToOne
    @JoinColumn(name = "business_user_id", nullable = true)
    private BusinessUser businessUser;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = true)
    private Booking booking;

    protected Payment() { }

    private Payment(Builder builder) {
        this.paymentId = builder.paymentId;
        this.cardNumber = builder.cardNumber;
        this.nameOfCardHolder = builder.nameOfCardHolder;
        this.expiryDate = builder.expiryDate;
        this.ccv = builder.ccv;
        this.amount = builder.amount;
        this.paymentDate = builder.paymentDate;
        this.paymentTime = builder.paymentTime;
        this.paymentStatus = builder.paymentStatus;
        this.user = builder.user;
        this.proUser = builder.proUser;
        this.businessUser = builder.businessUser;
        this.booking = builder.booking;
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

public String getCcv() { 
    return ccv; 
}

public BigDecimal getAmount() { 
    return amount; 
}

public LocalDate getPaymentDate() { 
    return paymentDate; 
}

public LocalTime getPaymentTime() { 
    return paymentTime; 
}

public PaymentStatus getPaymentStatus() { 
    return paymentStatus; 
}

public BasicUser getUser() { 
    return user; 
}

public ProUser getProUser() { 
    return proUser; 
}

public BusinessUser getBusinessUser() { 
    return businessUser; 
}

public Booking getBooking() { 
    return booking; 
}

    public void processPayment() {
        if (this.amount == null || this.amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero.");
        }
        this.paymentStatus = PaymentStatus.SUCCESSFUL;
        this.paymentDate = LocalDate.now();
        this.paymentTime = LocalTime.now();
    }

    @Override
public String toString() {
    return "Payment{" +
            "paymentId=" + getPaymentId() +
            ", cardNumber=" + cardNumber +
            ", nameOfCardHolder='" + nameOfCardHolder + '\'' +
            ", expiryDate=" + expiryDate +
            ", ccv='" + ccv + '\'' +
            ", amount=" + amount +
            ", paymentDate=" + paymentDate +
            ", paymentTime=" + paymentTime +
            ", paymentStatus=" + paymentStatus +
            ", userId=" + (user != null ? user.getUserId() : null) +
            ", bookingId=" + (booking != null ? booking.getBookingId() : null) +
            ", businessUserId=" + (businessUser != null ? businessUser.getUserId() : null) +
            ", proUserId=" + (proUser != null ? proUser.getUserId() : null) +
            '}';
}

    public static class Builder {
        private Long paymentId;
        private int cardNumber;
        private String nameOfCardHolder;
        private LocalDate expiryDate;
        private String ccv;
        private BigDecimal amount;
        private LocalDate paymentDate;
        private LocalTime paymentTime;
        private PaymentStatus paymentStatus;
        private BasicUser user;
        private ProUser proUser;
        private BusinessUser businessUser;
        private Booking booking;

        public Builder setPaymentId(Long paymentId) { 
            this.paymentId = paymentId; 
            return this; 
        }

        public Builder setCardNumber(int cardNumber) { 
            this.cardNumber = cardNumber; 
            return this; 
        }

        public Builder setNameOfCardHolder(String nameOfCardHolder) { 
            this.nameOfCardHolder = nameOfCardHolder; 
            return this; 
        }

        public Builder setExpiryDate(LocalDate expiryDate) { 
            this.expiryDate = expiryDate; 
            return this; 
        }

        public Builder setCcv(String ccv) { 
            this.ccv = ccv; 
            return this; 
        }

        public Builder setAmount(BigDecimal amount) { 
            this.amount = amount; 
            return this; 
        }

        public Builder setPaymentDate(LocalDate paymentDate) { 
            this.paymentDate = paymentDate; 
            return this; 
        }

        public Builder setPaymentTime(LocalTime paymentTime) { 
            this.paymentTime = paymentTime; 
            return this; 
        }

        public Builder setPaymentStatus(PaymentStatus paymentStatus) { 
            this.paymentStatus = paymentStatus; 
            return this; 
        }

        public Builder setUser(BasicUser user) { 
            this.user = user; 
            return this; 
        }

        public Builder setProUser(ProUser proUser) { 
            this.proUser = proUser; 
            return this; 
        }

        public Builder setBusinessUser(BusinessUser businessUser) { 
            this.businessUser = businessUser; 
            return this; 
        }

        public Builder setBooking(Booking booking) { 
            this.booking = booking; 
            return this; 
        }

        public Payment build() {
            if (user == null) {
                throw new IllegalStateException("Payment must have a payer (BasicUser).");
            }
            if ((proUser == null && businessUser == null) || (proUser != null && businessUser != null)) {
                throw new IllegalStateException("Payment must have exactly one recipient (ProUser or BusinessUser).");
            }
            return new Payment(this);
        }
    }
}
