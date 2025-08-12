package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.BasicUser;
import za.ac.cput.repository.BasicUserRepository;
import za.ac.cput.repository.CarRepository;

import java.util.List;

@Service
public class BasicUserService implements IBasicUserService {
    private IBasicUserService service;
    @Autowired
    private BasicUserRepository repository;


    @Override
    public BasicUser create(BasicUser basicUser) {
        return this.repository.save(basicUser);
    }

    @Override
    public BasicUser read(Long aLong) {
        return this.repository.findById(aLong).orElse(null);
    }

    @Override
    public BasicUser update(BasicUser basicUser) {
        return this.repository.save(basicUser);
    }

    @Override
    public boolean delete(Long aLong) {
        this.repository.deleteById(aLong);
        return true;
    }

    @Override
    public List<BasicUser> getAll() {
        return this.repository.findAll();
    }
}
