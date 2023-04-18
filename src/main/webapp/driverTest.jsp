<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: UserK
  Date: 2023-04-18
  Time: 오전 1:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>

<%
        Connection conn = null;

        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/test";
            String dbId = "root";
            String dbPw = "zerobase";

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("성공1");
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            System.out.println(conn + "성공2");
            System.out.println("Success");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println(e + "실패");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
%>
  <h2>DataBase 테스트</h2>
  </body>
</html>
