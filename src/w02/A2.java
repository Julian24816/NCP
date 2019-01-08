package w02;

import java.sql.*;

public class A2 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        ThreadLocal<Connection> localConnection = ThreadLocal.withInitial(() -> {
            try {
                return DriverManager.getConnection("jdbc:sqlite:test.db");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        new MyWorker(localConnection).run();
    }
}
