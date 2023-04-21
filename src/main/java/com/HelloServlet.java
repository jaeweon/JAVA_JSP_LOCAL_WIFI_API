package com;

import com.google.gson.Gson;
import com.vo.Wifi;
import org.mariadb.jdbc.Connection;

import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID =1L;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    public void init() {
        // db 연결
        try {
            Class.forName("org.mariadb.jdbc.Driver");
             conn = (Connection) DriverManager.getConnection(
                    "jdbc:mariadb://3.36.28.140:3306/seoul_wifi",
                    "ljw", "123qwe!"
            );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String path = request.getServletPath();

        System.out.println(path);
            if(path.equals("/getWifis")){
                // db에서 wifi 20개 가져오기
                stmt = conn.createStatement();
                List<Wifi> list = new ArrayList<>();
                try {
                    rs = stmt.executeQuery("select * from wifi limit 0, 20");
                    while (rs.next()){
                        Wifi wifi = new Wifi();
                        wifi.setWifi_idx(rs.getInt(1));
                        wifi.setAdmin_code(rs.getString(2));
                        wifi.setGoo(rs.getString(3));
                        wifi.setWifi_name(rs.getString(4));
                        wifi.setAddr1(rs.getString(5));
                        wifi.setAddr2(rs.getString(6));
                        wifi.setLocation(rs.getString(7));
                        wifi.setInstall_type(rs.getString(8));
                        wifi.setCompany(rs.getString(9));
                        wifi.setService_type(rs.getString(10));
                        wifi.setMang(rs.getString(11));
                        wifi.setInstall_year(rs.getInt(12));
                        wifi.setType(rs.getString(13));
                        wifi.setEnvironment(rs.getString(14));
                        wifi.setX(rs.getDouble(15));
                        wifi.setY(rs.getDouble(16));
                        wifi.setWork_date(rs.getString(17));
                        list.add(wifi);
                    }
                    Gson gson = new Gson();
                    String jsonObjectString = gson.toJson(list);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(jsonObjectString);

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
    }

    public void destroy() {
    }
}