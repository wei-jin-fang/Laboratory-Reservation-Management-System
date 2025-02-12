package com.example.idea_lab.mapper;

import com.example.idea_lab.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM accountinfo WHERE username = #{username}")
    User findById(@Param("username") String username);

    // 更新用户密码
    @Update("UPDATE accountinfo SET password = #{newPassword} WHERE username = #{username}")
    void updatePassword(@Param("username") String username, @Param("newPassword") String newPassword);


    @Insert("INSERT INTO accountinfo (username, password, email, level, phone, realname) " +
            "VALUES (#{username}, #{password}, #{email}, #{level}, #{phone}, #{realname})")
    void register(User user);

    @Select("SELECT COUNT(*) FROM accountinfo WHERE username = #{username}")
    int countByUsername(String username);

    @Select("SELECT * FROM accountinfo WHERE username = #{username} AND password = #{password}")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    @Select("SELECT * FROM accountinfo")
    List<User> findAllUsers();

    @Update("UPDATE accountinfo SET password = #{password} WHERE id = #{id}")
    void updateUserPassword(String id, String password);

    @Delete("DELETE FROM accountinfo WHERE id = #{id}")
    void deleteUser(String id);
}
