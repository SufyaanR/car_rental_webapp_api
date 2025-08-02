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
@DiscriminatorValue("SubscriptionPayment")
public class SubscriptionPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionPaymentId;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private LocalTime paymentTime;
    private RentalProvider rentalProvider;

    public SubscriptionPayment() {}

    public SubscriptionPayment(SubscriptionBuilder subscriptionBuilder) {
        this.subscriptionPaymentId = subscriptionBuilder.subscriptionPaymentId;
        this.amount = subscriptionBuilder.amount;
        this.paymentDate = subscriptionBuilder.paymentDate;
        this.paymentTime = subscriptionBuilder.paymentTime;
        this.rentalProvider = subscriptionBuilder.rentalProvider;
    }

    public Long getSubscriptionPaymentId() {
        return subscriptionPaymentId;
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

    public RentalProvider getRentalProvider() {
        return rentalProvider;
    }

    public static class SubscriptionBuilder {
        private Long subscriptionPaymentId;
        private RentalProvider rentalProvider;
        private BigDecimal amount;
        private LocalDate paymentDate;
        private LocalTime paymentTime;

        public SubscriptionBuilder setSubscriptionPaymentId(Long subscriptionPaymentId) {
            this.subscriptionPaymentId = subscriptionPaymentId;
            return this;
        }

        public SubscriptionBuilder setRentalProvider(RentalProvider rentalProvider) {
            this.rentalProvider = rentalProvider;
            return this;
        }

        public SubscriptionBuilder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public SubscriptionBuilder setPaymentDate(LocalDate paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public SubscriptionBuilder setPaymentTime(LocalTime paymentTime) {
            this.paymentTime = paymentTime;
            return this;
        }

        public SubscriptionPayment build() {
            return new SubscriptionPayment(this);
        }
    }
    
}
