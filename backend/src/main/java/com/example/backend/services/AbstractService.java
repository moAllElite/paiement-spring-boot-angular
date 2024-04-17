package com.example.backend.services;




import java.util.List;

public  interface AbstractService<T> {
    T findById(Long id);
    void delete(Long id);
    List<T> findAll();
}
