package com;

import java.sql.*;

public class NearWifiService {
    public void wifiSelect(NearWifi near){
        Connection conn = null;
        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/test";
            String dbId = "root";
            String dbPw = "zerobase";

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM wifiapi";

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                String wifiName = rs.getString("wifi_Name");
                String roadName = rs.getString("road_Name");
                String address = rs.getString("address");
                String installationType = rs.getString("installation_Type");
                System.out.println(wifiName + roadName + address + installationType);
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
}
