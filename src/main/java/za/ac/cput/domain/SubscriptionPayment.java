package za.ac.cput.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class SubscriptionPayment {

    private UUID subscriptionPaymentId;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private LocalTime paymentTime;
    private UUID rentalProviderId;

    public SubscriptionPayment() {}

    public SubscriptionPayment(SubscriptionBuilder subscriptionBuilder) {
        this.subscriptionPaymentId = subscriptionBuilder.subscriptionPaymentId;
        this.amount = subscriptionBuilder.amount;
        this.paymentDate = subscriptionBuilder.paymentDate;
        this.paymentTime = subscriptionBuilder.paymentTime;
        this.rentalProviderId = subscriptionBuilder.rentalProviderId;
    }

    public UUID getSubscriptionPaymentId() {
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

    public UUID getRentalProviderId() {
        return rentalProviderId;
    }

    public static class SubscriptionBuilder {
        private UUID subscriptionPaymentId;
        private UUID rentalProviderId;
        private BigDecimal amount;
        private LocalDate paymentDate;
        private LocalTime paymentTime;

        public SubscriptionBuilder setSubscriptionPaymentId(UUID subscriptionPaymentId) {
            this.subscriptionPaymentId = subscriptionPaymentId;
            return this;
        }

        public SubscriptionBuilder setRentalProviderId(UUID rentalProviderId) {
            this.rentalProviderId = rentalProviderId;
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
