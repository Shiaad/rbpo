package com.mtuci.rbpo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mtuci.rbpo.model.ProductDB;
import com.mtuci.rbpo.repository.ProductDBRepository;
import com.mtuci.rbpo.service.ProductDBService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductDBServiceImpl implements ProductDBService {

    private final ProductDBRepository productDBRepository;

    @Override
    public void save(ProductDB product) {
        productDBRepository.save(product);
    }

    @Override
    public List<ProductDB> findAll() {
        return productDBRepository.findAll();
    }

    @Override
    public Optional<ProductDB> findById(long id) {
        return productDBRepository.findById(id);
    }

    @Override
    public List<ProductDB> findByIsBlocked(boolean isBlocked) {
        return productDBRepository.findByIsBlocked(isBlocked);
    }

    @Override
    public void deleteById(long id) {
        productDBRepository.deleteById(id);
    }
}
