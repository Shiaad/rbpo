package com.mtuci.rbpo.repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtuci.rbpo.model.User;

@Repository
public interface UserDBRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameAndEmail(String login, String email);

    Boolean existsByUsername(String login);
    Boolean existsByEmail(String email);
}
