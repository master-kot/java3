import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatorDB {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:homework.db");
        Statement stmt = conn.createStatement();

        DBUtility dbUtility = new DBUtility();

        //CreatorDB.createListOfEquipment(stmt);
        //dbUtility.createPrinterTable(conn, stmt);
        //dbUtility.AddPrinters(stmt);
        //System.out.println(dbUtility.selectExpensivePC(stmt));
        //System.out.println(dbUtility.selectQuickLaptop(stmt));
        //dbUtility.AddPrinters(stmt, new Printer(1111, "Kyosera", "blue", "laser", 5555));
        //System.out.println(dbUtility.selectMaker(stmt));
        //dbUtility.makerWithMaxProceeds(stmt);
        //CreatorDB.createCommonBase(stmt);
    }
    public static void createListOfEquipment (Statement stmt) {
        try {
            stmt.execute("CREATE TABLE IF NOT EXISTS Product (maker TEXT, model INTEGER, type TEXT);");
            stmt.execute("CREATE TABLE IF NOT EXISTS Laptop (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, model INTEGER, speed INTEGER, ram INTEGER, hd INTEGER, screen INTEGER, price INTEGER);");
            stmt.execute("CREATE TABLE IF NOT EXISTS PC (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, model INTEGER, speed INTEGER, ram INTEGER, hd INTEGER, price INTEGER);");
            stmt.execute("INSERT INTO Laptop(model, speed, ram, hd, screen, price)  VALUES(1360, 3600, 16, 1000, 19, 70000);");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('Intel', 1360, 'Laptop');");
            stmt.execute("INSERT INTO PC(model, speed, ram, hd, price)  VALUES(2210, 3600, 8, 1000, 35000);");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('Intel', 2210, 'PC');");
            stmt.execute("INSERT INTO PC(model, speed, ram, hd, price)  VALUES(2205, 2200, 4, 1000, 22000);");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('AMD', 2205, 'PC');");
            stmt.execute("INSERT INTO PC(model, speed, ram, hd, price)  VALUES(2205, 2200, 4, 1000, 22000);");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('AMD', 2205, 'PC');");
            stmt.execute("INSERT INTO Laptop(model, speed, ram, hd, screen, price)  VALUES(1330, 3000, 8, 1000, 17, 35000);");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('Dell', 1330, 'Laptop');");
            stmt.execute("INSERT INTO Laptop(model, speed, ram, hd, screen, price)  VALUES(1305, 2000, 4, 500, 15, 15000);");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('HP', 1305, 'Laptop');");
            stmt.execute("INSERT INTO Laptop(model, speed, ram, hd, screen, price)  VALUES(1305, 2000, 4, 500, 15, 15000);");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('HP', 1305, 'Laptop');");
            stmt.execute("INSERT INTO Laptop(model, speed, ram, hd, screen, price)  VALUES(1305, 2000, 4, 500, 15, 15000);");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('HP', 1305, 'Laptop');");
            stmt.execute("INSERT INTO PC(model, speed, ram, hd, price)  VALUES(2210, 3600, 8, 1000, 35000);");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('Intel', 2210, 'PC');");
            stmt.execute("INSERT INTO Laptop(model, speed, ram, hd, screen, price)  VALUES(1305, 2000, 4, 500, 15, 15000);");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('HP', 1305, 'Laptop');");
            stmt.execute("INSERT INTO Laptop(model, speed, ram, hd, screen, price)  VALUES(1305, 2000, 4, 500, 15, 5000);");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('HP', 1305, 'Laptop');");
            stmt.execute("INSERT INTO Laptop(model, speed, ram, hd, screen, price)  VALUES(1290, 1200, 2, 500, 14, 10000);");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('Acer', 1290, 'Laptop');");
            stmt.execute("INSERT INTO Laptop(model, speed, ram, hd, screen, price)  VALUES(1290, 1200, 2, 500, 14, 10000);");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('Acer', 1290, 'Laptop');");
            stmt.execute("INSERT INTO Laptop(model, speed, ram, hd, screen, price)  VALUES(1290, 1200, 2, 500, 14, 10000);");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('Acer', 1290, 'Laptop');");
            stmt.execute("INSERT INTO PC(model, speed, ram, hd, price)  VALUES(2200, 1700, 2, 500, 12000);");
            stmt.execute("INSERT INTO PC(model, speed, ram, hd, price)  VALUES(2200, 1700, 2, 500, 12000);");
            stmt.execute("INSERT INTO PC(model, speed, ram, hd, price)  VALUES(2200, 1700, 2, 500, 12000);");
            stmt.execute("INSERT INTO PC(model, speed, ram, hd, price)  VALUES(2200, 1700, 2, 500, 12000);");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('NEC', 2200, 'PC');");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('NEC', 2200, 'PC');");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('NEC', 2200, 'PC');");
            stmt.execute("INSERT INTO Product(maker, model, type) VALUES('NEC', 2200, 'PC');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createCommonBase (Statement stmt){
        try {
            stmt.execute("CREATE TABLE IF NOT EXISTS Common(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, maker TEXT, model INTEGER, type TEXT, speed INTEGER, ram INTEGER, hd INTEGER, screen INTEGER, price INTEGER);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('Intel', 1360, 'Laptop', 3600, 16, 1000, 19, 70000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('Intel', 2210, 'PC', 3600, 8, 1000, 20, 35000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('AMD', 2205, 'PC', 2200, 4, 1000, 20, 22000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('AMD', 2205, 'PC', 2200, 4, 1000, 20, 22000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('Dell', 1330, 'Laptop', 3000, 8, 1000, 17, 35000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('HP', 1305, 'Laptop', 2000, 4, 500, 15, 15000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('HP', 1305, 'Laptop', 2000, 4, 500, 15, 15000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('HP', 1305, 'Laptop', 2000, 4, 500, 15, 15000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('Intel', 2210, 'PC', 3600, 8, 1000, 20, 35000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('HP', 1305, 'Laptop', 2000, 4, 500, 15, 150000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('HP', 1305, 'Laptop', 2000, 4, 500, 15, 5000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('Acer', 1290, 'Laptop', 1200, 2, 500, 14, 10000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('Acer', 1290, 'Laptop', 1200, 2, 500, 14, 10000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('Acer', 1290, 'Laptop', 1200, 2, 500, 14, 10000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('NEC', 2200, 'PC', 1700, 2, 500, 20, 12000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('NEC', 2200, 'PC', 1700, 2, 500, 20, 12000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('NEC', 2200, 'PC', 1700, 2, 500, 20, 12000);");
            stmt.execute("INSERT INTO Common(maker, model, type, speed, ram, hd, screen, price) VALUES('NEC', 2200, 'PC', 1700, 2, 500, 20, 12000);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}