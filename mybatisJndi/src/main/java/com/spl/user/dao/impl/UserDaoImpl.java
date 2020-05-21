package com.spl.user.dao.impl;

import com.spl.user.dao.IUserDao;
import com.spl.user.domain.QueryVo;
import com.spl.user.domain.User;
import com.spl.user.domain.UserDiffProperty;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory)
    {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<User> findAll() {
        this.sqlSession = sqlSessionFactory.openSession();
        List<User> userList = this.sqlSession.selectList("com.spl.user.dao.IUserDao.findAll");
        this.sqlSession.close();
        return userList;
    }

    public List<User> findAllAnnotation() {
        return null;
    }

    public void saveUser(User user) {

    }

    public void updateUser(User user) {

    }

    public void deleteUser(Integer id) {

    }

    public User findById(Integer id) {
        return null;
    }

    public List<User> findByName(String UserName) {
        return null;
    }

    public int findUserTotal() {
        return 0;
    }

    public List<User> findByQueryVo(QueryVo vo) {
        return null;
    }

    public List<UserDiffProperty> findDiffPropertyAll() {
        return null;
    }

    public List<UserDiffProperty> findDiffPropertyAll2() {
        return null;
    }

    public List<UserDiffProperty> findUserByCondition(UserDiffProperty user) {
        return null;
    }

    public List<UserDiffProperty> findUserByInIds(QueryVo queryVo) {
        return null;
    }
}
