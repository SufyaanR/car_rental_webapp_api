package za.ac.cput.service;

import java.util.List;

public interface IService<T, ID> {
    T save(T entity);
    T findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
}
