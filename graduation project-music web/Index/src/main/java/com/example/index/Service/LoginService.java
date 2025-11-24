package com.example.index.Service;

import com.example.index.domain.dto.RegisterDto;
import com.example.index.domain.po.Result;
import com.example.index.domain.po.User;

public interface LoginService {

    Result register(RegisterDto registerDto);

    Result login(User user);

}
