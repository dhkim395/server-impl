package com.koreait.serverimpl.service;

import com.koreait.serverimpl.dto.LoginDTO;
import com.koreait.serverimpl.dto.UserDTO;
import com.koreait.serverimpl.mapper.UserMapper;
import com.koreait.serverimpl.model.User;
import com.koreait.serverimpl.util.JwtUtil;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    public void join(UserDTO dto) {
        if (userMapper.findByUsername(dto.getUsername()) != null) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); // 실제 서비스에선 해싱 필요
        user.setEmail(dto.getEmail());

        userMapper.insertUser(user);
    }

    public String login(LoginDTO dto) {
        User user = userMapper.findByUsername(dto.getUsername());

        if (user == null || !user.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
        return jwtUtil.generateToken(user.getUsername());
    }
    public User getUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public void updateUser(String username, UserDTO dto) {
        User user = userMapper.findByUsername(username);
        if (user == null) throw new RuntimeException("사용자 없음");

        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // 보통은 해시처리 필요

        userMapper.updateUser(user);
    }

}