package com.example.index.domain.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String nickname;
    private String phone;
    private String password;
    private String smsCode;

}
