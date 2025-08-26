package za.ac.cput.service;

import java.util.Optional;

import za.ac.cput.domain.ProUser;

public interface IProUserService extends IService<ProUser, Long> {

    ProUser register(ProUser user);
    boolean login(String username, String password);
    Optional<ProUser> findByUsername(String username);
    
}