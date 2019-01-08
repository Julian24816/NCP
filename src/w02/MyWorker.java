package w02;

import java.sql.*;

public class MyWorker implements Runnable {
    private final ThreadLocal<Connection> localConnection;

    MyWorker(ThreadLocal<Connection> localConnection) {
        this.localConnection = localConnection;
    }


    @Override
    public void run() {
        try {
            Connection conn = localConnection.get();
            Statement stat = conn.createStatement();
            stat.executeUpdate("drop table if exists people;");
            stat.executeUpdate("create table people (name, occupation);");
            PreparedStatement prep = conn.prepareStatement(
                    "insert into people values (?, ?);");

            prep.setString(1, "Gandhi");
            prep.setString(2, "politics");
            prep.addBatch();
            prep.setString(1, "Turing");
            prep.setString(2, "computers");
            prep.addBatch();
            prep.setString(1, "Wittgenstein");
            prep.setString(2, "smartypants");
            prep.addBatch();

            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            ResultSet rs = stat.executeQuery("select * from people;");
            while (rs.next()) {
                System.out.println("name = " + rs.getString("name"));
                System.out.println("job = " + rs.getString("occupation"));
            }
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
