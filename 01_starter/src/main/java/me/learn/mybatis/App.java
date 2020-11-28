package me.learn.mybatis;

import me.learn.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * @Author Aran
 * @Date 2020/11/28 下午4:29
 */
public class App {
    public static void main(String[] args) throws IOException, SQLException {
        //1. 读取 xml
        String resource = "mybatis-cfg.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //2. 得到SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //3. 得到SqlSession
        try (SqlSession session = sqlSessionFactory.openSession()) {
            User user = (User) session.selectOne("me.learn.mybatis.mapper.UserMapper.getUserById", 101);
            System.out.println(user.toString());
        }

     }
}
