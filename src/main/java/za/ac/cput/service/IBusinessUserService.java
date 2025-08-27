package za.ac.cput.service;

import java.util.Optional;
import za.ac.cput.domain.BusinessUser;

public interface IBusinessUserService extends IService<BusinessUser, Long> {

    BusinessUser register(BusinessUser user);
    boolean login(String username, String password);
    Optional<BusinessUser> findByUsername(String username);
    
}
