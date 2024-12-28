package com.hermes.hermes.service;

import com.hermes.hermes.dto.User;
import com.hermes.hermes.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class My_Page_Service_Impl implements My_Page_Service {
      // Fix circular reference error 작동을 안해서 주석으로 처리했음.
//    @Autowired
//    private My_Page_Service my_Page_Service;
    private final UserMapper userMapper;

    public My_Page_Service_Impl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User My_Page_Get_User_Info(int reg_num) {
        return userMapper.My_Page_Get_User_Info(reg_num); // My_Page_Get_User_Info() static 에러
    }
}
