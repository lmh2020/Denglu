package Dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class connectionUtils {
    private static String username ="root";
    private static String password ="123456";
    private static String url="jdbc:mysql://localhost:3306/yeye?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    private static String driver="com.mysql.cj.jdbc.Driver";
    private static Connection conn  ;
    private static Statement ts ;
    private static ResultSet rs;
    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //封装连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
    //封装流的关闭
    public static void jdbcClose(ResultSet rs,Statement ts,Connection conn) throws SQLException {
        try{
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if(rs!=null){
                rs=null;
            }
        }
        ts.close();
        if(ts!=null){
            ts=null;
        }
        conn.close();
        if(conn!=null){
            conn=null;
        }
    }
    //更新封装
    public static int update(String sql,Object...argv) throws SQLException {
        //定义一个变量PreparedStatement
        PreparedStatement ps =null;
        //定义一个Connection类型的变量
        Connection conn;
        conn=getConnection();
        ps=conn.prepareStatement(sql);//预编译完成
        //设置值
        for(int i=0;i<argv.length;i++){
            //索引是从1开始，所以是i+1
            ps.setObject(i+1,argv[i]);
        }
        int bset=ps.executeUpdate();
        System.out.println(bset);
        //关闭资源
        jdbcClose(ps,conn);
        return bset;
    }
    //查询封装
    public static   <T> T query(Class<T> clazz,String sql,Object...argv) throws SQLException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        T entity=null;
        PreparedStatement ps;
        Connection conn;
        conn=getConnection();
        ps=conn.prepareStatement(sql);
        for(int i=0;i<argv.length;i++){
            ps.setObject(i+1,argv[i]);
        }
        ResultSet rs =ps.executeQuery();
        //创建一个集合，用于存储属性和对应的属相值
        //创建实例
        entity=clazz.newInstance();
        Map<String,Object> map =new HashMap<String,Object>();
        //得到ResultSetMetaData对象，获取此 ResultSet 对象的列的编号、类型和属性。
            ResultSetMetaData rsmd =rs.getMetaData();
        if(rs.next()){
            //获取总列数并且进行遍历
            for(int i=0;i<rsmd.getColumnCount();i++){
                //列的名称
                //getColumnLabel获取用于打印输出和显示的指定列的建议标题,索引从1开始
                String filename=rsmd.getColumnName(i+1);
                //列值
                Object obj=rs.getObject(filename);
                map.put(filename,obj);
            }

        }
        //遍历map
        for(Map.Entry<String,Object> entry:map.entrySet()){
            String filename2=entry.getKey();
            Object obj2=entry.getValue();
            Field f =entity.getClass().getDeclaredField(filename2);
            f.setAccessible(true);
            f.set(entity,obj2);
        }
        return entity;
    }

    private static void jdbcClose(PreparedStatement ps, Connection conn) throws SQLException {
        ps.close();
        if(ps!=null){
            ps=null;
        }
        conn.close();
        if(conn!=null){
            conn=null;
        }
    }



}
