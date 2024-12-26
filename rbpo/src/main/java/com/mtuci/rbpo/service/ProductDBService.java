package com.mtuci.rbpo.service;

import java.util.List;
import java.util.Optional;

import com.mtuci.rbpo.model.ProductDB;

public interface ProductDBService {
    void save(ProductDB product);
    List<ProductDB> findAll();
    Optional<ProductDB> findById(long id);
    List<ProductDB> findByIsBlocked(boolean isBlocked);
    void deleteById(long id);
}
