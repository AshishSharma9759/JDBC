
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class InventoryManagement {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/retail_db";
        String username = "root";
        String password = "975972";

        String query = "INSERT INTO Product "
                + "(product_id, product_name, category, quantity, price) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con =
                DriverManager.getConnection(url, username, password);
             PreparedStatement ps = con.prepareStatement(query)) {

            // Product 1
            insertProduct(ps, 101, "Laptop",
                    "Electronics", 10, 55000.0);

            // Product 2
            insertProduct(ps, 102, "Mouse",
                    "Electronics", 15, 500.0);

            // Product 3
            insertProduct(ps, 103, "Chair",
                    "Furniture", 25, 2500.0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void insertProduct(PreparedStatement ps,
            int id, String name, String category,
            int quantity, double price) {

        try {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, category);
            ps.setInt(4, quantity);
            ps.setDouble(5, price);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Product inserted successfully!");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Duplicate Product ID: " + id);
        } catch (SQLException e) {
            System.out.println("Insertion failed: " + e.getMessage());
        }
    }
}

