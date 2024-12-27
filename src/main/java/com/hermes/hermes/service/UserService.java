package com.hermes.hermes.service;
import com.hermes.hermes.dto.Product;
import com.hermes.hermes.dto.User;
import org.springframework.ui.Model;

import java.awt.*;
import java.util.List;
import java.util.Map;

public interface UserService {
    List<Map<String, Object>> getAllUsers();
    void insertUser(User user);
    String getImage(int productId);
}