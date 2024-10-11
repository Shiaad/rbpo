package com.mtuci.rbpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mtuci.rbpo.model.UserDB;

@Repository
public interface UserRepository extends JpaRepository<UserDB, Long> {
    UserDB findByName(String name);
}
