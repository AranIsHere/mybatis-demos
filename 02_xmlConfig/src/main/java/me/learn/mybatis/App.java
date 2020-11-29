package me.learn.mybatis;
import me.learn.mybatis.mapper.UserMapper;
import me.learn.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Author Aran
 * @Date 2020/11/28 下午4:29
 */

public class App {
    public static void main(String[] args) throws Exception {
        //1. 读取 xml
        String resource = "mybatis-cfg.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //2. 得到SqlSessionFactory
        /*
        * SqlSessionFactoryBuilder
        * 这个类可以被实例化、使用和丢弃，一旦创建了 SqlSessionFactory，就不再需要它了。
        * 因此 SqlSessionFactoryBuilder 实例的最佳作用域是方法作用域（也就是局部方法变量）。
        * 你可以重用 SqlSessionFactoryBuilder 来创建多个 SqlSessionFactory 实例，
        * 但最好还是不要一直保留着它，以保证所有的 XML 解析资源可以被释放给更重要的事情。
        * */
    //    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

        /**
         * 通过build方法读取配置properties文件
         */
        InputStream propIn = Resources.getResourceAsStream("config.properties");
        Properties prop = new Properties();
        prop.load(propIn);
        SqlSessionFactory sqlSessionFactory1 = new SqlSessionFactoryBuilder().build(in,"development",prop);




        //3. 得到SqlSession
        /**
         * SqlSessionFactory
         *
         * SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。
         * 使用 SqlSessionFactory 的最佳实践是在应用运行期间不要重复创建多次，
         * 多次重建 SqlSessionFactory 被视为一种代码“坏习惯”。因此 SqlSessionFactory 的最佳作用域是应用作用域。
         * 有很多方法可以做到，最简单的就是使用单例模式或者静态单例模式。
         */

        /**
         * SqlSession
         *
         * 每个线程都应该有它自己的 SqlSession 实例。
         * SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。
         * 绝对不能将 SqlSession 实例的引用放在一个类的静态域，甚至一个类的实例变量也不行。
         * 也绝不能将 SqlSession 实例的引用放在任何类型的托管作用域中，比如 Servlet 框架中的 HttpSession。
         * 如果你现在正在使用一种 Web 框架，考虑将 SqlSession 放在一个和 HTTP 请求相似的作用域中。
         * 换句话说，每次收到 HTTP 请求，就可以打开一个 SqlSession，返回一个响应后，就关闭它。
         * 这个关闭操作很重要，为了确保每次都能执行关闭操作，你应该把这个关闭操作放到 finally 块中。
         * 下面的示例就是一个确保 SqlSession 关闭的标准模式：
         */
        try (SqlSession session = sqlSessionFactory1.openSession()) {
            User user = (User) session.selectOne("me.learn.mybatis.mapper.UserMapper.getUserById", 101);
            System.out.println(user.toString());
//            System.out.println("=================================");
//            UserMapper userMapper = session.getMapper(UserMapper.class);
//            System.out.println(userMapper.getUserById(101));
        }



     }
}
