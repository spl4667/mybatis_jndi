<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="com.spl.user.dao.IUserDao" %>
<%@ page import="com.spl.user.domain.User" %>
<%@ page import="java.util.List" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<html>
<%
    InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
    SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
    List<User> userList = iUserDao.findAll();
    for(User user : userList)
    {
        System.out.println(user);
    }
    sqlSession.close();
    inputStream.close();
%>
<body>
<h2>Hello World!</h2>
</body>
</html>
