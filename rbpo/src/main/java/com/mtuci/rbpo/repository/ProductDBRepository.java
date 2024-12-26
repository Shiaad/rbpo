package com.mtuci.rbpo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtuci.rbpo.model.ProductDB;

@Repository
public interface ProductDBRepository extends JpaRepository<ProductDB, Long> {
    List<ProductDB> findByIsBlocked(boolean isBlocked);
}
