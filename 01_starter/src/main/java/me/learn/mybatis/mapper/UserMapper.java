package me.learn.mybatis.mapper;

import me.learn.mybatis.pojo.User;

/**
 * @Author Aran
 * @Date 2020/11/28 下午5:51
 */
public interface UserMapper {
    public User getUserById(int id);
}
