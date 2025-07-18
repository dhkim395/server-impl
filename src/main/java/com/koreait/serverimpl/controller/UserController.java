package com.koreait.serverimpl.controller;

import com.koreait.serverimpl.dto.LoginDTO;
import com.koreait.serverimpl.dto.UserDTO;
import com.koreait.serverimpl.model.User;
import com.koreait.serverimpl.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public String join(@RequestBody UserDTO dto) {
        userService.join(dto);
        return "회원가입 완료!";
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {
        return userService.login(dto); // JWT 토큰 반환
    }
    @GetMapping("/me")
    public User getMyInfo(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        return userService.getUserByUsername(username);
    }

    @PutMapping("/me")
    public String updateMyInfo(@RequestBody UserDTO dto, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        userService.updateUser(username, dto);
        return "회원 정보가 수정되었습니다.";
    }


}
