package com.mtuci.rbpo.service;
import java.util.List;
import com.mtuci.rbpo.model.UserDB;

public interface UserService {
    void save(UserDB user);
    List<UserDB> findAll();
    UserDB findById(long id);
}