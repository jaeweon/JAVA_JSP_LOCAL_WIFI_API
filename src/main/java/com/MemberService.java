package com;

import java.sql.*;

public class MemberService {

public void dbSelect(){
    Connection conn = null;
    try {
        String jdbcUrl = "jdbc:mysql://localhost:3306/test";
        String dbId = "root";
        String dbPw = "zerobase";

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
        Statement statement = conn.createStatement();
        String sql = "SELECT * FROM member";

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()){
            String memberType = rs.getString("member_type");
            String userId = rs.getString("user_id");
            String pw = rs.getString("pw");
            String name = rs.getString("name");
            System.out.println(memberType + userId + pw + name);
        }

        if(!rs.isClosed()){
            rs.close();
        }

        if(!statement.isClosed()){
            statement.close();
        }
        if(!conn.isClosed()){
            conn.close();
        }

    }catch (ClassNotFoundException e){
        e.printStackTrace();
    }
    catch (SQLException e){
        e.printStackTrace();
    }

}
    public void register(Member member){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String jdbcUrl = "jdbc:mysql://localhost:3306/test";
        String dbId = "root";
        String dbPw = "zerobase";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            Statement statement = conn.createStatement();
            String sql = " insert into member (member_type, user_id, pw, name) " +
            " values (?, ?, ?, ?); ";
            System.out.println(sql);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, member.getMemberType());
            preparedStatement.setString(2, member.getUserId());
            preparedStatement.setString(3, member.getPw());
            preparedStatement.setString(4, member.getName());

            int affected = preparedStatement.executeUpdate();
            if(affected > 0){
                System.out.println("저장 성공");
            }else System.out.println("씰패");

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void dbUpdate(){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String memberType = "ema21il";
        String memberId = "dds22ss@ads";
        String memberPw = "203013";

        String jdbcUrl = "jdbc:mysql://localhost:3306/test";
        String dbId = "root";
        String dbPw = "zerobase";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            Statement statement = conn.createStatement();
            String sql = " update member set " +
                    " pw = ? " +
                    " where member_type = ? and user_id = ? ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, memberPw);
            preparedStatement.setString(2, memberType);
            preparedStatement.setString(3, memberId);

            int affected = preparedStatement.executeUpdate();
            if(affected > 0){
                System.out.println("저장 성공");
            }else System.out.println("씰패");

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void withDraw(Member member){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String jdbcUrl = "jdbc:mysql://localhost:3306/test";
        String dbId = "root";
        String dbPw = "zerobase";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            Statement statement = conn.createStatement();
            String sql = " delete member from member " +
                    " where member_type = ? and user_id = ? ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, member.getMemberType());
            preparedStatement.setString(2, member.getUserId());

            int affected = preparedStatement.executeUpdate();
            if(affected > 0){
                System.out.println("저장 성공");
            }else System.out.println("씰패");

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
