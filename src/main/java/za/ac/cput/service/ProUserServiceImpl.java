package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.ProUser;
import za.ac.cput.repository.IProUserRepository;
import java.util.List;

@Service
public abstract class ProUserServiceImpl implements IProUserService {

    private final IProUserRepository repository;

    @Autowired
    public ProUserServiceImpl(IProUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProUser create(ProUser proUser) {
        // Business logic/validation here
        return this.repository.save(proUser);
    }

    @Override
    public ProUser read(String userId) {
        return this.repository.findById(userId).orElse(null);
    }

    @Override
    public ProUser update(ProUser proUser) {
        if (this.repository.existsById(proUser.getUserId())) {
            return this.repository.save(proUser);
        }
        return null; // or throw exception
    }

    @Override
    public boolean delete(String userId) {
        if (this.repository.existsById(userId)) {
            this.repository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public List<ProUser> getAll() {
        return this.repository.findAll();
    }
}