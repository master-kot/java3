

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
    /*
    Каждый из тасков решается одним SQL запросом
    Создать таблицу принтеры, Printer(id INTEGER AI PK U, model INTEGER, color TEXT, type TEXT, price INTEGER)
    добавить в нее 3 записи:
    1 1012 col laser 20000 (производитель HP)
    2 1010 bw jet 5000 (производитель Canon)
    3 1010 bw jet 5000 (производитель Canon)
    Каждая вставка в таблицу принтер должна отражаться добавлением записи в таблицу продукт
    */

    void AddPrinters(Statement stmt) {
        // TODO: 16.12.2019
        try {
            stmt.execute(String.format("INSERT INTO Printer (model, color, type, price) VALUES (%d, %s, %s, %d);", 1012, "'col'", "'laser'", 20000));
            stmt.execute(String.format("INSERT INTO Product (maker, model, type) VALUES (%s, %d, %s);", "'HP'", 1012, "'Printer'"));
            stmt.execute(String.format("INSERT INTO Printer (model, color, type, price) VALUES (%d, %s, %s, %d);", 1010, "'bw'", "'jet'", 5000));
            stmt.execute(String.format("INSERT INTO Product (maker, model, type) VALUES (%s, %d, %s);", "'Canon'", 1010, "'Printer'"));
            stmt.execute(String.format("INSERT INTO Printer (model, color, type, price) VALUES (%d, %s, %s, %d);", 1010, "'bw'", "'jet'", 5000));
            stmt.execute(String.format("INSERT INTO Product (maker, model, type) VALUES (%s, %d, %s);", "'Canon'", 1010, "'Printer'"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void AddPrinters(Statement stmt, Printer printer) {
        // TODO: 16.12.2019
        try {
            stmt.execute(String.format("INSERT INTO Printer (model, color, type, price) VALUES (%d, '%s', '%s', %d);", printer.getModel(), printer.getColor(), printer.getType(), printer.getPrice()));
            stmt.execute(String.format("INSERT INTO Product (maker, model, type) VALUES ('%s', %d, '%s');", printer.getMaker(), printer.getModel(), printer.getType()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPrinterTable(Connection con, Statement  stmt) {
        // TODO: 16.12.2019
        try {
            stmt.execute("CREATE TABLE IF NOT EXISTS Printer(id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, model INTEGER, color TEXT, type TEXT, price INTEGER);");
            stmt.execute("CREATE TABLE IF NOT EXISTS Product (maker TEXT, model INTEGER, type TEXT);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод должен вернуть список уникальных моделей PC дороже 15 тысяч
    // Метод возвращает: [2210, 2205]
    public ArrayList<String> selectExpensivePC(Statement stmt) {
        //todo
        ArrayList<String> result = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT model FROM PC WHERE price > 15000");
        while (rs.next()) {
            String currentPrinter = rs.getString("model");
            if (!result.contains(currentPrinter)) result.add(currentPrinter);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Метод должен вернуть список id ноутов, скорость процессора которых выше чем 2500
    // [1, 2]
    public ArrayList<Integer> selectQuickLaptop(Statement stmt){
        // TODO: 16.12.2019
        ArrayList<Integer> result = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT id FROM Laptop WHERE speed > 2500");
            while (rs.next()) {
                result.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Метод должен вернуть список производителей которые делают и пк и ноутбуки
    // Метод возвращает: [Intel]
    public ArrayList<String> selectMaker(Statement stmt){
        //todo
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> pcMakers = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT maker FROM Product WHERE type = 'PC'");
            while (rs.next()) {
                pcMakers.add(rs.getString("maker"));
            }
            rs = stmt.executeQuery("SELECT maker FROM Product WHERE type = 'Laptop'");
            while (rs.next()) {
                String currentMaker = rs.getString("maker");
                if (pcMakers.contains(currentMaker) & !result.contains(currentMaker)) result.add(currentMaker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * Метод должен вернуть максимальную среди всех произодителей суммарную стоимость всех изделий типов ноутбук или компьютер,
     * произведенных одним производителем. Необходимо объединить таблицы продуктов ноутбуков и компьютеров
     * и отгрупировать по сумме прайсов после чего выбрать максимум или сделать любым другим способом
     * Метод возвращает: 140 000
     */

    public int makerWithMaxProceeds(Statement stmt){
        int maxPrice = 0;
        //todo
        //поскольку метод JOIN мы не проходили я не смог его корректно реализовать, но логику работы алгоритма могу написать
        //на примере объединенной базы данных Common
        ArrayList<String> listMakers = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT maker FROM Common WHERE type = 'PC' or type = 'Laptop'");
            while (rs.next()) {
                String currentMaker = rs.getString("maker");
                if (!listMakers.contains(currentMaker)) listMakers.add(currentMaker);
            }
            for (int i = 0; i <listMakers.size() ; i++) {
                int currentMakerPrice = 0;
                ArrayList<Integer> listModels = new ArrayList<>();
                rs = stmt.executeQuery(String.format("SELECT price FROM Common WHERE maker = '%s' and type = 'PC' or maker = '%s' and type = 'Laptop'", listMakers.get(i), listMakers.get(i)));
                while (rs.next()) {
                    //int currentModel = rs.getInt("model");
                    int currentPrice =  rs.getInt("price");
                    //if (!listModels.contains(currentModel)) {
                    //listModels.add(currentModel);
                        currentMakerPrice += currentPrice;
                    //}
                }
                if (currentMakerPrice > maxPrice) maxPrice = currentMakerPrice;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxPrice;
    }


    public void createListOfEquipment (Statement stmt) {
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

    public void createCommonBase (Statement stmt){
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
