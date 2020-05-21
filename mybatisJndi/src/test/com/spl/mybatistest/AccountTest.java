package com.spl.mybatistest;

import com.spl.account.dao.IAccountDao;
import com.spl.account.domain.Account;
import com.spl.account.domain.AccountUser;
import com.spl.user.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountTest {
    private InputStream inputStream;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;
    private IAccountDao iAccount;

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
        iAccount = sqlSession.getMapper(IAccountDao.class);
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
     * 测试查询所有
     */
    @Test
    public void TestFindAll()
    {
        List<Account> accountList = iAccount.findAll();
        for (Account accout : accountList) {
            System.out.println(accout);
        }
    }

    /**
     * 测试查询所有账户及用户信息
     */
    @Test
    public void TestFindAllAccountUser()
    {
        List<AccountUser> aus = iAccount.findAccountUser();
        for (AccountUser au : aus) {
            System.out.println(au);
        }
    }
}
