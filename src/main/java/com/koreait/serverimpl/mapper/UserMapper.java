package com.koreait.serverimpl.mapper;

import com.koreait.serverimpl.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(User user);
    User findByUsername(String username);
    void updateUser(User user);
}

