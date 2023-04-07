package com.project.demo05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
/*
数据库的连接及增删查改
 */

public class MysqlTest {
    //logger为抽象接口
    private static final Logger log = LoggerFactory.getLogger(MysqlTest.class);
    static String URL = "jdbc:mysql://localhost:3307/db?serverTimezone=GMT%2B8";//东八区时间
    static String username = "root";
    static String password = "123456";

    public static void main(String[] args) {
        try {
            //数据库驱动加载
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(URL, username, password);

            log.info("数据库连接测试");
            //创建statement，执行sql
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from student");//executeQuery用于select
            log.info(String.valueOf(rs));
            while(rs.next()){
                log.info("id" + rs.getString("id"));  //getString（n）获取第n列的内容,n从1开始
                log.info("name" + rs.getString("name"));
                log.info("sex" + rs.getString("sex"));
            }

            String sql = "insert into student values ('10','小白','男',null,null )";   //SQL语句
            String sql1 = "update student set sex = '女' where id = 10";
            String sql2 = "delete from student where id= 10";
            st.executeUpdate(sql2);//executeUpdate执行 INSERT、UPDATE 或 DELETE 语句以及 SQL DDL（数据定义语言）语句,返回值为一个整数代表受影响的行数
            log.info("数据更新成功");

            rs.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            log.error("bad things", e);
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            log.error("error things", throwables);
        }
    }

}
