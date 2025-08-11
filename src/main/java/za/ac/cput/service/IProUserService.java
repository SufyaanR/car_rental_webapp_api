package za.ac.cput.service;

import za.ac.cput.domain.ProUser;
import java.util.List;

public interface IProUserService {
    ProUser save(ProUser proUser);

    ProUser create(ProUser proUser);

    ProUser read(String userId);

    ProUser update(ProUser proUser);

    boolean delete(String userId);
    List<ProUser> findAll();

    List<ProUser> getAll();
}