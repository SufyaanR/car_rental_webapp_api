package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.ProUser;
import za.ac.cput.repository.ProUserRepository;
import za.ac.cput.service.IProUserService;
import java.util.List;

@Service
public class ProUserServiceImpl implements IProUserService {

    @Autowired
    private ProUserRepository repository;

    @Override
    public ProUser create(ProUser proUser) {
        return this.repository.save(proUser);
    }

    @Override
    public ProUser read(String userId) {
        return this.repository.findById(userId).orElse(null);
    }

    @Override
    public ProUser update(ProUser proUser) {
        return this.repository.save(proUser);
    }

    @Override
    public boolean delete(String userId) {
        this.repository.deleteById(userId);
        return true;
    }

    @Override
    public List<ProUser> getAll() {
        return this.repository.findAll();
    }
}