package classWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Mikhail\\IdeaProjects\\Java31\\homework.db");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS Printer(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " model Integer, color TEXT, price INTEGER);");
//        stmt.execute("INSERT INTO Printer(model, color, price)  VALUES(120, 'col', 1000);");
//        stmt.execute("INSERT INTO Printer(model, color, price) VALUES(120, 'col', 1000);");
//        stmt.execute("INSERT INTO Printer(model, color, price) VALUES(113, 'col', 1005);");
//        System.out.println(stmt.executeUpdate("UPDATE Printer SET model = 12 WHERE model = 120;"));
        stmt.executeUpdate("DELETE from Printer where model = 12;");
    }
}
