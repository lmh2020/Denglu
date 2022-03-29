package Dao;

import User.User;

import java.sql.SQLException;

public class UserDao {
    public static boolean addUser(User u) throws SQLException {
        boolean r =false;
        String sql ="insert into lmh(username,password) values(?,?)";
        int num= connectionUtils.update(sql,u.getUsername(),u.getPassword());
        if(num>0){
            r=true;
        }
        return r;
    }
    //查询用户
    public User queryByName(String username) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        String sql ="select * from lmh where username=?";
        User user=connectionUtils.query(User.class,sql,username);
        return  user;
    }



}
