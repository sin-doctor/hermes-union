package com.hermes.hermes.mapper;

import com.hermes.hermes.dto.Purchase;
import com.hermes.hermes.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUsers();

    // 유저의 등록번호를 받아 해당 유저의 전체 정보를 받아옴
    User My_Page_Get_User_Info(int reg_num);

    // My_Page 에서 History(구매내역) 보여주기 위해서 모든 구매 내역 조회
    List<Purchase> My_Page_GetAllPurchase();

    // 현재 로그인한 유저의 등록번호를 받아옴
    int My_Page_Current_User_reg_num();
}
