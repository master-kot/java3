package classWork;

import java.sql.*;
import java.util.Enumeration;

public class Ex1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Mikhail\\IdeaProjects\\Java31\\homework.db");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT distinct * from Laptop join Product on Laptop.model = Product.model " +
                "WHERE Laptop.price > 10000 order by price;");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("maker") + " " + rs.getInt("model") + " " +
                    rs.getInt("price"));
        }
        //SELECT -> table
        //INSERT -> change db
        //UPDATE -> int (count of changed records) change db
        //DELETE -> int (count od deleted records) change db
    }
}
