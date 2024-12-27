package com.hermes.hermes.service;

import com.hermes.hermes.dto.User;
import com.hermes.hermes.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class My_Page_Service_Impl implements My_Page_Service {
    @Override
    public User My_Page_Get_User_Info(int reg_num) {
        return UserMapper.My_Page_Get_User_Info(reg_num); // My_Page_Get_User_Info() static 에러
    }
}
