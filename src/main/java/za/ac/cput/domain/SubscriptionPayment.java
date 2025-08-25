package za.ac.cput.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
public class SubscriptionPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionPaymentId;

    private BigDecimal amount;
    private LocalDate paymentDate;
    private LocalTime paymentTime;

    @ManyToOne
    @JoinColumn(name = "pro_user_id", nullable = true)
    private ProUser proUser;

    @ManyToOne
    @JoinColumn(name = "business_user_id", nullable = true)
    private BusinessUser businessUser;

    protected SubscriptionPayment() {}

    private SubscriptionPayment(Builder builder) {
        this.subscriptionPaymentId = builder.subscriptionPaymentId;
        this.amount = builder.amount;
        this.paymentDate = builder.paymentDate;
        this.paymentTime = builder.paymentTime;
        this.proUser = builder.proUser;
        this.businessUser = builder.businessUser;
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

    public ProUser getProUser() { 
        return proUser; 
    }

    public BusinessUser getBusinessUser() { 
        return businessUser; 
    }

    @Override
public String toString() {
    return "SubscriptionPayment{" +
            "paymentId=" + getSubscriptionPaymentId() +
            ", amount=" + amount +
            ", paymentDate=" + paymentDate +
            ", paymentTime=" + paymentTime +
            ", businessUserId=" + (businessUser != null ? businessUser.getUserId() : null) +
            ", proUserId=" + (proUser != null ? proUser.getUserId() : null) +
            '}';
}

    public static class Builder {
        private Long subscriptionPaymentId;
        private BigDecimal amount;
        private LocalDate paymentDate;
        private LocalTime paymentTime;
        private ProUser proUser;
        private BusinessUser businessUser;

        public Builder setSubscriptionPaymentId(Long subscriptionPaymentId){
            this.subscriptionPaymentId = subscriptionPaymentId; 
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

        public Builder setProUser(ProUser proUser) { 
            this.proUser = proUser; 
            return this; 
        }

        public Builder setBusinessUser(BusinessUser businessUser) { 
            this.businessUser = businessUser; 
            return this; 
        }

        public SubscriptionPayment build() {
            if (Stream.of(proUser, businessUser).filter(Objects::nonNull).count() != 1) {
                throw new IllegalStateException("SubscriptionPayment must have exactly one recipient (ProUser or BusinessUser).");
            }
            return new SubscriptionPayment(this);
        }
    }
}
