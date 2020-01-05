import java.sql.*;
import java.util.ArrayList;

public class DBUtility {

    // TODO: 16.12.2019
    /**
     * Создать таблицу принтеры, Printer(id INTEGER AI PK U, model INTEGER, color TEXT, type TEXT, price INTEGER)
     * добавить в нее 3 записи:
     * 1 1012 col laser 20000 (производитель HP)
     * 2 1010 bw jet 5000 (производитель Canon)
     * 3 1010 bw jet 5000 (производитель Canon)
     * Каждая вставка в таблицу принтер должна отражаться добавлением записи в таблицу продукт
    */
    void AddPrinters(Statement stmt) {
        try {
            stmt.execute(String.format("INSERT INTO Printer (model, color, type, price) VALUES (%d, %s, %s, %d);"
                    , 1012, "'col'", "'laser'", 20000));
            stmt.execute(String.format("INSERT INTO Product (maker, model, type) VALUES (%s, %d, %s);"
                    , "'HP'", 1012, "'Printer'"));
            stmt.execute(String.format("INSERT INTO Printer (model, color, type, price) VALUES (%d, %s, %s, %d);"
                    , 1010, "'bw'", "'jet'", 5000));
            stmt.execute(String.format("INSERT INTO Product (maker, model, type) VALUES (%s, %d, %s);"
                    , "'Canon'", 1010, "'Printer'"));
            stmt.execute(String.format("INSERT INTO Printer (model, color, type, price) VALUES (%d, %s, %s, %d);"
                    , 1010, "'bw'", "'jet'", 5000));
            stmt.execute(String.format("INSERT INTO Product (maker, model, type) VALUES (%s, %d, %s);"
                    , "'Canon'", 1010, "'Printer'"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO: 16.12.2019
    /**
     * Аналогичный предыдущему метод, работающий с объектом класса Printer, добавляющий его значения в базу данных
     */
    void AddPrinters(Statement stmt, Printer printer) {
        try {
            stmt.execute(String.format("INSERT INTO Printer (model, color, type, price) VALUES (%d, '%s', '%s', %d);"
                    , printer.getModel(), printer.getColor(), printer.getType(), printer.getPrice()));
            stmt.execute(String.format("INSERT INTO Product (maker, model, type) VALUES ('%s', %d, '%s');"
                    , printer.getMaker(), printer.getModel(), printer.getType()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO: 16.12.2019
    /**
     * Создает таблицы продуктов и принтеров, если они не были созданы и добавляет в них все принтеры
     */
    public void createPrinterTable(Connection con, Statement  stmt) {
        try {
            stmt.execute("CREATE TABLE IF NOT EXISTS Printer(id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                    " model INTEGER, color TEXT, type TEXT, price INTEGER);");
            stmt.execute("CREATE TABLE IF NOT EXISTS Product (maker TEXT, model INTEGER, type TEXT);");
            AddPrinters(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO: 16.12.2019
    /**
     * Метод должен вернуть список уникальных моделей PC дороже 15 тысяч
     */
    public ArrayList<String> selectExpensivePC(Statement stmt) {
        ArrayList<String> result = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT distinct model FROM PC WHERE price > 15000");
        while (rs.next()) {
            result.add(rs.getString("model"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // TODO: 16.12.2019
    /**
     * Метод должен вернуть список id ноутов, скорость процессора которых выше чем 2500
     */
    public ArrayList<Integer> selectQuickLaptop(Statement stmt){
        ArrayList<Integer> result = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT id FROM Laptop WHERE speed > 2500");
            while (rs.next()) {
                result.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // TODO: 16.12.2019
    /**
     * Метод должен вернуть список производителей которые делают и пк и ноутбуки
     */
    public ArrayList<String> selectMaker(Statement stmt){
        ArrayList<String> result = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("select maker, count(maker) as counter from \n" +
                    "(select DISTINCT * from product) group by maker having counter = 2;");
            while (rs.next()) {
                result.add(rs.getString("maker"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // TODO: 16.12.2019
    /**
     * Метод должен вернуть максимальную среди всех произодителей суммарную стоимость всех изделий типов ноутбук или компьютер,
     * произведенных одним производителем. Необходимо объединить таблицы продуктов ноутбуков и компьютеров
     * и отгрупировать по сумме прайсов после чего выбрать максимум или сделать любым другим способом
     */
    public int makerWithMaxProceeds(Statement stmt){
        int maxPrice = 0;
        ArrayList<String> listMakers = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT MAX(SUMM) as ANS from(select DISTINCT maker, sum(price) as SUMM from \n" +
                    "(select distinct id, maker, price from PC join Product on pc.model = product.model\n" +
                    "UNION\n" +
                    "select distinct id, maker, price from Laptop join Product on Laptop.model = product.model) \n" +
                    "group by maker);");
            rs.next();
            maxPrice = rs.getInt("ANS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxPrice;
    }
}