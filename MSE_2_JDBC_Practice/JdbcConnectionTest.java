import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionTest {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/retail_db";
        String username = "root";
        String password = "975972";

        try {
            Connection con = DriverManager.getConnection(
                url, username, password
            );

            System.out.println("Database connected successfully!");

            con.close();

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}

