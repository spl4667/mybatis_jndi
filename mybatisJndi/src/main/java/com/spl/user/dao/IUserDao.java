package com.spl.user.dao;

import com.spl.user.domain.QueryVo;
import com.spl.user.domain.User;
import com.spl.user.domain.UserDiffProperty;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.IntegerTypeHandler;

import java.util.List;

/**
 * @author pengl
 *
 * 用户持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 查询所有操作-注解方式
     * @return
     */
    @Select("select * from user")
    List<User> findAllAnnotation();

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 查询一个操作
     * @return
     */
    User findById(Integer id);

    /**
     * 模糊查询
     * @return
     */
    List<User> findByName(String UserName);

    /**
     * 查询用户数
     * @return
     */
    int findUserTotal();

    /**
     * 根据QueryVo查询用户信息
     * @return
     */
    List<User> findByQueryVo(QueryVo vo);

    /**
     * 查询所有操作_实体类属性与数据库字段不对应
     * @return
     */
    List<UserDiffProperty> findDiffPropertyAll();

    /**
     * 查询所有操作_实体类属性与数据库字段不对应
     * @return
     */
    List<UserDiffProperty> findDiffPropertyAll2();

    /**
     * 查询所有操作_实体类属性与数据库字段不对应
     * @return
     */
    List<UserDiffProperty> findUserByCondition(UserDiffProperty user);

    /**
     * 查询多个id
     * @param queryVo
     * @return
     */
    List<UserDiffProperty> findUserByInIds(QueryVo queryVo);
}
