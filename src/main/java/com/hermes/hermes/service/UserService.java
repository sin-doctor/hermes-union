package com.hermes.hermes.service;

import com.hermes.hermes.dto.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<Map<String, Object>> getAllUsers();

    void insertUser(User user);

}
