package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.BusinessUser;
import za.ac.cput.repository.BusinessUserRepository;
import za.ac.cput.util.Helper;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BusinessUserServiceImpl implements IBusinessUserService {

    private final BusinessUserRepository businessUserRepository;

    @Autowired
    public BusinessUserServiceImpl(BusinessUserRepository businessUserRepository) {
        this.businessUserRepository = businessUserRepository;
    }

    @Override
    public BusinessUser save(BusinessUser user) {
        return businessUserRepository.save(user);
    }

    @Override
    public BusinessUser register(BusinessUser user) {
        if (!Helper.isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email");
        }

        if (!Helper.isValidPhoneNumber(user.getPhoneNumber(), "ZA")) {
            throw new IllegalArgumentException("Invalid phone number");
        }

        return businessUserRepository.save(user);
    }

    @Override
    public boolean login(String username, String password) {
        Optional<BusinessUser> userOpt = businessUserRepository.findByUsername(username);
        return userOpt.map(user -> user.login(username, password)).orElse(false);
    }

    @Override
    public Optional<BusinessUser> findByUsername(String username) {
        return businessUserRepository.findByUsername(username);
    }

    @Override
    public BusinessUser findById(Long id) {
        return businessUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BusinessUser not found"));
    }

    @Override
    public List<BusinessUser> findAll() {
        return businessUserRepository.findAll();
    }

    public BusinessUser update(Long id, BusinessUser updates) {
        BusinessUser existing = findById(id);

        BusinessUser updated = new BusinessUser.Builder()
                .setUserId(existing.getUserId())
                .setFirstName(updates.getFirstName() != null ? updates.getFirstName() : existing.getFirstName())
                .setLastName(updates.getLastName() != null ? updates.getLastName() : existing.getLastName())
                .setEmail(updates.getEmail() != null ? updates.getEmail() : existing.getEmail())
                .setPhoneNumber(updates.getPhoneNumber() != null ? updates.getPhoneNumber() : existing.getPhoneNumber())
                .setUsername(updates.getUsername() != null ? updates.getUsername() : existing.getUsername())
                .setPassword(updates.getPassword() != null ? updates.getPassword() : existing.getPassword())
                .build();

        return businessUserRepository.save(updated);
    }

    @Override
    public void deleteById(Long id) {
        businessUserRepository.deleteById(id);
    }
}

