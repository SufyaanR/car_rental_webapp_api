package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.BasicUser;
import za.ac.cput.repository.BasicUserRepository;
import za.ac.cput.util.Helper;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BasicUserService implements IBasicUserService {

    private final BasicUserRepository basicUserRepository;

    @Autowired
    public BasicUserService(BasicUserRepository basicUserRepository) {
        this.basicUserRepository = basicUserRepository;
    }

    @Override
    public BasicUser save(BasicUser user) {
        return basicUserRepository.save(user);
    }

    @Override
    public BasicUser register(BasicUser user) {
        if (!Helper.isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email");
        }

        if (!Helper.isValidPhoneNumber(user.getPhoneNumber(), "ZA")) {
            throw new IllegalArgumentException("Invalid phone number");
        }

        return basicUserRepository.save(user);
    }

    @Override
    public boolean login(String username, String password) {
        Optional<BasicUser> userOpt = basicUserRepository.findByUsername(username);
        return userOpt.map(user -> user.login(username, password)).orElse(false);
    }

    @Override
    public Optional<BasicUser> findByUsername(String username) {
        return basicUserRepository.findByUsername(username);
    }

    @Override
    public BasicUser findById(Long id) {
        return basicUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BasicUser not found"));
    }

    @Override
    public List<BasicUser> findAll() {
        return basicUserRepository.findAll();
    }

    public BasicUser update(Long id, BasicUser updates) {
        BasicUser existing = findById(id);

        BasicUser updated = new BasicUser.Builder()
                .setUserId(existing.getUserId())
                .setFirstName(updates.getFirstName() != null ? updates.getFirstName() : existing.getFirstName())
                .setLastName(updates.getLastName() != null ? updates.getLastName() : existing.getLastName())
                .setEmail(updates.getEmail() != null ? updates.getEmail() : existing.getEmail())
                .setPhoneNumber(updates.getPhoneNumber() != null ? updates.getPhoneNumber() : existing.getPhoneNumber())
                .setUsername(updates.getUsername() != null ? updates.getUsername() : existing.getUsername())
                .setPassword(updates.getPassword() != null ? updates.getPassword() : existing.getPassword())
                .setBookCar(updates.getBookCar() != null ? updates.getBookCar() : existing.getBookCar())
                .build();

        return basicUserRepository.save(updated);
    }

    @Override
    public void deleteById(Long id) {
        basicUserRepository.deleteById(id);
    }

}