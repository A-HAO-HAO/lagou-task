package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //调用
        User user = new User();
        user.setId(1);
        user.setUsername("tom22");
      /*  User user2 = sqlSession.selectOne("user.selectOne", user);

        System.out.println(user2);*/

       /* List<User> users = sqlSession.selectList("user.selectList");
        for (User user1 : users) {
            System.out.println(user1);
        }*/

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }

        User byCondition = userDao.findByCondition(user);
        System.out.println(byCondition);
    }

    SqlSession sqlSession;

    @Before
    public void before() throws Exception{
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void testInsert() throws Exception {
        User user = new User();
        user.setId(8);
        user.setUsername("CUIHAO");
        user.setPassword("888888888888888");
        user.setBirthday("1999-11-11");
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        int i = userDao.addUser(user);
        if(i > 0){
            System.out.println("插入成功");
        }
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setId(8);
        user.setUsername("CUIHAO-UPDATE");
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        int i = userDao.updateUserNameById(user);
        if(i > 0){
            System.out.println("更新成功");
        }
    }

    @Test
    public void deleteUser() throws Exception {
        User user = new User();
        user.setId(8);
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        int i = userDao.deleteUser(user);
        if(i > 0){
            System.out.println("删除成功");
        }
    }

}
