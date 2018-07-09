package com.mr.mysite.dao;

import com.mr.mysite.domain.UserDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty ="id" , keyColumn = "id", before = false, resultType = int.class)
    @Insert("insert into t_user(name,password,phone,operator,operationdate,username,sex) values (#{name},#{password},#{phone},#{operator},#{operationDate},#{userName},#{sex})")
    @Options(useGeneratedKeys = true, keyColumn = "id" ,keyProperty = "id")
    public int insert(UserDto userDto);

    @Update("update t_user set name=#{name},password=#{password},phone=#{phone},operator=#{operator},operaiondate=#{operationDate},username=#{userName},sex=#{sex} where id=#{id}")
    int update(UserDto userDto);

    @Select("select * from t_user")
    public List<UserDto> findAll();

    @Select("select * from t_user where id = #{id}")
    public UserDto findById(int id);
}
