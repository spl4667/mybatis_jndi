package com.spl.mybatistest;

import com.spl.user.dao.IUserDao;
import com.spl.user.dao.impl.UserDaoImpl;
import com.spl.user.domain.QueryVo;
import com.spl.user.domain.User;
import com.spl.user.domain.UserDiffProperty;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyBatisTest {

    private InputStream inputStream;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;
    private IUserDao iUserDao;

    /**
     * 用于在测试类之前执行
     */
    @Before
    public void Init() throws Exception
    {
        //读取配置文件
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //使用工厂生产SqlSession对象
        sqlSession = sqlSessionFactory.openSession();
        //使用SqlSession创建Dao接口的代理对象
        iUserDao = sqlSession.getMapper(IUserDao.class);
    }

    /**
     * 用于在测试类之后执行
     */
    @After
    public void Destroy() throws Exception
    {
        //提交
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        inputStream.close();
    }

    /**
     * 测试查询
     * @throws Exception
     */
    @Test
    public void TestQuery () throws Exception
    {
        //使用代理对象执行方法
        List<User> userList = iUserDao.findAll();
        for(User user : userList)
        {
            System.out.println(user);
        }

        System.out.println("==========================Annotation==============================");
        List<User> userListAnnotation = iUserDao.findAllAnnotation();
        for(User user : userListAnnotation)
        {
            System.out.println(user);
        }

        System.out.println("==========================Impl Class==============================");
        iUserDao = new UserDaoImpl(sqlSessionFactory);
        List<User> userListImplClass = iUserDao.findAll();
        for(User user : userListImplClass)
        {
            System.out.println(user);
        }
    }

    /**
     * 测试保存
     */
    @Test
    public void TestSave()
    {
        User user = new User();
        user.setUsername("mybatis");
        user.setAddress("浙江");
        user.setBirthday(new Date());
        user.setSex("男");
        System.out.println("保存之前：" + user);
        //执行保存
        this.iUserDao.saveUser(user);
        System.out.println("保存之后：" + user);
    }

    /**
     * 测试修改
     */
    @Test
    public void TestUpdate()
    {
        User user = new User();
        user.setId(5);
        user.setUsername("mybatis");
        user.setAddress("浙江");
        user.setBirthday(new Date());
        user.setSex("男");
        //执行更新
        this.iUserDao.updateUser(user);
    }

    /**
     * 测试删除
     */
    @Test
    public void TestDelete()
    {
        //执行删除
        this.iUserDao.deleteUser(5);
    }

    /**
     * 测试查询一个
     * @throws Exception
     */
    @Test
    public void TestFindOne() throws Exception
    {
        //使用代理对象执行方法
        User user = iUserDao.findById(3);
        System.out.println(user);
    }

    /**
     * 测试模糊查询
     * @throws Exception
     */
    @Test
    public void TestFindByLike () throws Exception
    {
        //使用代理对象执行方法
        List<User> userList = iUserDao.findByName("%张%");
        for(User user : userList)
        {
            System.out.println(user);
        }
    }

    /**
     * 测试查询用户数
     * @throws Exception
     */
    @Test
    public void TestFindUserTotal () throws Exception
    {
        //使用代理对象执行方法
        int count = iUserDao.findUserTotal();
        System.out.println(count);
    }

    /**
     * 测试QueryVo查询
     * @throws Exception
     */
    @Test
    public void TestFindByQueryVo () throws Exception
    {
        User user = new User();
        user.setUsername("%张%");
        QueryVo queryVo = new QueryVo();
        queryVo.setUser(user);
        //使用代理对象执行方法
        List<User> userList = iUserDao.findByQueryVo(queryVo);
        for(User u : userList)
        {
            System.out.println(u);
        }
    }

    /**
     * 测试查询_实体类属性与数据库字段不对应
     * @throws Exception
     */
    @Test
    public void TestQueryOfDiffPropery () throws Exception
    {
        //使用代理对象执行方法
        List<UserDiffProperty> userList = iUserDao.findDiffPropertyAll();
        for(UserDiffProperty user : userList)
        {
            System.out.println(user);
        }

        System.out.println("==========================Result Map==============================");
        List<UserDiffProperty> userListDiffPropery = iUserDao.findDiffPropertyAll2();
        for(UserDiffProperty user : userListDiffPropery)
        {
            System.out.println(user);
        }
    }

    /**
     * 测试条件查询
     * @throws Exception
     */
    @Test
    public void TestQueryByCondition () throws Exception
    {
        UserDiffProperty userDiffProperty = new UserDiffProperty();
        userDiffProperty.setUserName("张三");
        //使用代理对象执行方法
        List<UserDiffProperty> userList = iUserDao.findUserByCondition(userDiffProperty);
        for(UserDiffProperty user : userList)
        {
            System.out.println(user);
        }
    }

    /**
     * 测试条件查询
     * @throws Exception
     */
    @Test
    public void TestQueryByIdsIn () throws Exception
    {
        QueryVo queryVo = new QueryVo();
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        ids.add(4);
        queryVo.setIds(ids);
        //使用代理对象执行方法
        List<UserDiffProperty> userList = iUserDao.findUserByInIds(queryVo);
        for(UserDiffProperty user : userList)
        {
            System.out.println(user);
        }
    }
}
