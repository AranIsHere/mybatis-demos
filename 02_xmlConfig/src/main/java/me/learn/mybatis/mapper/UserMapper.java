package me.learn.mybatis.mapper;

import me.learn.mybatis.pojo.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;

/**
 * @Author Aran
 * @Date 2020/11/28 下午5:51
 */
public interface UserMapper {


    public User getUserById(int id);

    @Select("SELECT * FROM User where phone= #{phone}")
    public User getUserByPhoneNo(String phone);
}
