package com.hermes.hermes.service;

import com.hermes.hermes.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hermes.hermes.mapper.UserMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Map<String, Object>> getAllUsers() {
        List<User> userList = userMapper.getAllUsers();
        return userList.stream().map(user -> {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("userId", user.getId());
            userMap.put("username", user.getName());
            userMap.put("userPw", user.getPassword());
            userMap.put("user_verification_answer",user.getUser_verification_answer());
            userMap.put("history",user.getHistory());
            return userMap;
        }).collect(Collectors.toList());
    }
    @Override
    public void insertUser(User user){
        userMapper.insertUser(user);
    }
}