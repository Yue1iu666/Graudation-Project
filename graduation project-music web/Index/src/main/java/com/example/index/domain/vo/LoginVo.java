package com.example.index.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {
    private Integer id;
    private String nickname;
    private String password;
    private String token;
}
