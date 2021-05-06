package com.community.my.community.mapper;

import com.community.my.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 12922
 */
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER (ACCOUNT_ID,NAME,TOKEN,GMT_CREATE,GMT_LAST_UPDATE) VALUES (#{accountId},#{name},#{token},#{gmtCreate},#{gmtLastUpdata})")
    void insert(User user);
    @Select("SELECT * FROM USER WHERE TOKEN = #{token}")
    User findByToken(@Param("token") String token);
}
