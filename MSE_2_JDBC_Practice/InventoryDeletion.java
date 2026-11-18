
import java.sql.*;
import java.util.Scanner;

public class InventoryDeletion {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/retail_db";
        String username = "root";
        String password = "975972";

        Scanner sc = new Scanner(System.in);

        try (Connection con =
                DriverManager.getConnection(url, username, password)) {

            // Calculate total inventory value
            String sumQuery =
                    "SELECT SUM(quantity * price) AS total_value FROM Product";

            try (PreparedStatement ps = con.prepareStatement(sumQuery);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    System.out.println("Total Inventory Value: "
                            + rs.getDouble("total_value"));
                }
            }

            // Accept product ID
            System.out.print("Enter Product ID to delete: ");
            int id = sc.nextInt();

            // Delete only if quantity is zero
            String deleteQuery =
                    "DELETE FROM Product WHERE product_id = ? AND quantity = 0";

            try (PreparedStatement ps = con.prepareStatement(deleteQuery)) {

                ps.setInt(1, id);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Product deleted successfully!");
                } else {
                    System.out.println(
                            "Product cannot be deleted! It does not exist "
                            + "or its quantity is not zero.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
