package com.example.index.domain.po;


import lombok.Data;


import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;
    private String nickname;
    private String phone;
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
