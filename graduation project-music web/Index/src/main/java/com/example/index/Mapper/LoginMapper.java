package com.example.index.Mapper;

import com.example.index.domain.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Insert("insert into musicproject.user(nickname,phone,password,create_time,update_time) values(#{nickname},#{phone},#{password},#{createTime},#{updateTime})")
    void Register(User user);

    @Select("select * from musicproject.user where phone = #{phone} ")
    User login(User user);
}
