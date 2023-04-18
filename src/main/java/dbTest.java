import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import java.sql.*;
public class dbTest {

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
    public void dbInsert(){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String memberType = "email";
        String memberId = "ddsss@ads";
        String memberPw = "123422";
        String memberName = "teee";

        String jdbcUrl = "jdbc:mysql://localhost:3306/test";
        String dbId = "root";
        String dbPw = "zerobase";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
            Statement statement = conn.createStatement();
            String sql = " insert into member (member_type, user_id, pw, name) " +
            " values (?, ?, ?, ?); ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, memberType);
            preparedStatement.setString(2, memberId);
            preparedStatement.setString(3, memberPw);
            preparedStatement.setString(4, memberName);

            int affected = preparedStatement.executeUpdate(sql);
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
