package com.example.index.Controller;

import com.example.index.Service.LoginService;
import com.example.index.domain.dto.RegisterDto;
import com.example.index.domain.po.Result;
import com.example.index.domain.po.User;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    /**
     * 注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody RegisterDto registerDto) {
        return loginService.register(registerDto);
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user ) {
        return loginService.login(user);
    }
}
