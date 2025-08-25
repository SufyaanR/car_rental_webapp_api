package za.ac.cput.service;
// BasicUser Service Interface
import java.util.Optional;
import za.ac.cput.domain.BasicUser;

public interface IBasicUserService extends IService <BasicUser, Long>{

    BasicUser register(BasicUser basicuser);
    boolean login(String username, String password);
    Optional<BasicUser> findByUsername(String username);

}
