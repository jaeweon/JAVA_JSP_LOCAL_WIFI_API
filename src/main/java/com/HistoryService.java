package com;

import java.sql.*;

public class HistoryService {
    public void insertHistory(Member member){
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
}
