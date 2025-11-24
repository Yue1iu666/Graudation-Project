package com.example.index.Service.Impl;

import com.example.index.Mapper.LoginMapper;
import com.example.index.Service.LoginService;
import com.example.index.Utils.JwtUtils;
import com.example.index.domain.dto.RegisterDto;
import com.example.index.domain.po.Result;
import com.example.index.domain.po.User;
import com.example.index.domain.vo.LoginVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginMapper loginMapper;
    private final PasswordEncoder passwordEncoder;

    //注册功能
    @Override
    public Result register(RegisterDto registerDto) {
        //对原始密码进行 Bcrypt加密
        String password = passwordEncoder.encode(registerDto.getPassword());
        User user = new User();
        user.setNickname(registerDto.getNickname());
        user.setPhone(registerDto.getPhone());
        user.setPassword(password);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        loginMapper.Register(user);
        return Result.success();
    }

    //登录功能
    @Override
    public Result login(User user) {
        //先从数据库中查询数据
        User login = loginMapper.login(user);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (login != null){
             //在使用BcryptPasswordEncoder进行密码校验
            if (encoder.matches(user.getPassword(), login.getPassword())) {
                //密码匹配成功,生成令牌
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", login.getId());
                claims.put("nickname", login.getNickname());
                claims.put("password", login.getPassword());
                String token = JwtUtils.generateJwt(claims);
                return Result.success(
                        new LoginVo(login.getId(), login.getNickname(), login.getPassword(), token)
                );
            } else {
                return Result.error("密码错误");
            }
        }
        return Result.error("用户或者密码错误");
    }
}