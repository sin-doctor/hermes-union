package com.hermes.hermes.service;

import com.hermes.hermes.dto.Product;
import com.hermes.hermes.dto.Purchase;
import com.hermes.hermes.dto.User;
import com.hermes.hermes.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



public interface My_Page_Service {
    // 유저의 등록번호를 받아 해당 유저의 전체 정보를 받아옴
    User My_Page_Get_User_Info(int reg_num);
}
