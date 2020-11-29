package me.learn.mybatis.pojo;

import java.io.Serializable;

/**
 * @Author Aran
 * @Date 2020/11/28 下午5:41
 */
// implements Serializable
public class User {
    int id;
    String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
