import java.sql.*;
import java.util.Arrays;


public class DBClass {
  
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:homework.db");
        Statement stmt = conn.createStatement();

        DBUtility dbUtility = new DBUtility();
        //dbUtility.createListOfEquipment(stmt);
        //dbUtility.createPrinterTable(conn, stmt);
        //dbUtility.AddPrinters(stmt);
        //System.out.println(dbUtility.selectExpensivePC(stmt));
        //System.out.println(dbUtility.selectQuickLaptop(stmt));
        //dbUtility.AddPrinters(stmt, new Printer(1111, "Kyosera", "blue", "laser", 5555));
        //System.out.println(dbUtility.selectMaker(stmt));
        dbUtility.makerWithMaxProceeds(stmt);
        //dbUtility.createCommonBase(stmt);


        //PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE id = ?");
        //ps.setInt(1, 2);
        //ResultSet rs = stmt.executeQuery("SELECT * FROM users");
        //ResultSet rs = ps.executeQuery();
        // int result = stmt.executeUpdate("INSERT INTO Students (Name, GroupName, Score) VALUES ("Bob", "Tbz11", 80);");

        conn.close();


        

/*        String fieldName1 = "id";
        String fieldName2 = "msg";
        stmt.execute("CREATE TABLE IF NOT EXISTS lol(" + fieldName1 + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " + fieldName2 + " TEXT);");
        String type = "'Laptop'";
        ResultSet rs = stmt.executeQuery("SELECT DISTINCT * FROM Product WHERE type=" + type + ";");
        while (rs.next()) {
            System.out.println(rs.getString("maker") + " "
            + rs.getInt("model") + " " + rs.getString("type"));
        }
        stmt.execute("CREATE table IF NOT EXISTS users(id INTEGER, name TEXT);");
        //PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id, name) values(?, ?);");
        for (int i = 0; i < 100; i++) {
            User u = new User(i+1, "Ivan " + (i + 1));
            stmt.executeUpdate("INSERT INTO users(id, name) VALUES (" + u.getId() +", '" +u.getName() +"');");
        }

            private class Maker {
        private String name;
        private ArrayList<Integer> models;
        private int price;

        Maker (String name, int model, int price) {
            this.name = name;
            this.models = new ArrayList<>();
            this.models.add(model);
            this.price = price;
        }

        public String getName() {
            models.toArray().
            return this.name;
        }

        boolean checkModel (int model) {
            return this.models.contains(model);
        }

        int getPrice () {
            return this.price;
        }

        void addModel (int model) {
            this.models.add(model);
        }

        void addPrice (int price) {
            this.price += price;
        }
    }
    */
    }
}
