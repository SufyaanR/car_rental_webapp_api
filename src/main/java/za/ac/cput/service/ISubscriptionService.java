package za.ac.cput.service;

import za.ac.cput.domain.SubscriptionPayment;
import java.util.*;

public interface ISubscriptionService {
    
    
    SubscriptionPayment save(SubscriptionPayment subscriptionPayment);
    SubscriptionPayment read(UUID subscriptionPaymentId);
    void delete(UUID subscriptionPaymentId);
    List<SubscriptionPayment> findall();
    

}
