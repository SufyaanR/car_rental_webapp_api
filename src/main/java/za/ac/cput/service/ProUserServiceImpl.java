package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.ProUser;
import za.ac.cput.repository.ProUserRepository;
import za.ac.cput.util.Helper;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProUserServiceImpl implements IProUserService {

    private final ProUserRepository proUserRepository;

    @Autowired
    public ProUserServiceImpl(ProUserRepository proUserRepository) {
        this.proUserRepository = proUserRepository;
    }

    @Override
    public ProUser save(ProUser user) {
        return proUserRepository.save(user);
    }

    @Override
    public ProUser register(ProUser user) {
        if (!Helper.isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email");
        }

        if (!Helper.isValidPhoneNumber(user.getPhoneNumber(), "ZA")) {
            throw new IllegalArgumentException("Invalid phone number");
        }

        return proUserRepository.save(user);
    }

    @Override
    public boolean login(String username, String password) {
        Optional<ProUser> userOpt = proUserRepository.findByUsername(username);
        return userOpt.map(user -> user.login(username, password)).orElse(false);
    }

    @Override
    public Optional<ProUser> findByUsername(String username) {
        return proUserRepository.findByUsername(username);
    }

    @Override
    public ProUser findById(Long id) {
        return proUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProUser not found"));
    }

    @Override
    public List<ProUser> findAll() {
        return proUserRepository.findAll();
    }

    public ProUser update(Long id, ProUser updatedUser) {
        ProUser existingUser = findById(id);

        ProUser userToSave = new ProUser.Builder()
                .setUserId(existingUser.getUserId()) 
                .setUsername(updatedUser.getUsername() != null ? updatedUser.getUsername() : existingUser.getUsername())
                .setEmail(updatedUser.getEmail() != null ? updatedUser.getEmail() : existingUser.getEmail())
                .setPhoneNumber(updatedUser.getPhoneNumber() != null ? updatedUser.getPhoneNumber() : existingUser.getPhoneNumber())
                .setPassword(updatedUser.getPassword() != null ? updatedUser.getPassword() : existingUser.getPassword())
                .setFirstName(updatedUser.getFirstName() != null ? updatedUser.getFirstName() : existingUser.getFirstName())
                .setLastName(updatedUser.getLastName() != null ? updatedUser.getLastName() : existingUser.getLastName())
                .build();

        return proUserRepository.save(userToSave);
    }

    @Override
    public void deleteById(Long id) {
        proUserRepository.deleteById(id);
    }
}
