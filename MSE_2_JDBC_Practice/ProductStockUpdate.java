
import java.sql.*;
import java.util.Scanner;

public class ProductStockUpdate {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/retail_db";
        String username = "root";
        String password = "975972";

        String query = "UPDATE Product SET quantity = quantity + ? "
                     + "WHERE product_id = ?";

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        System.out.print("Enter quantity to add: ");
        int qty = sc.nextInt();

        try (Connection con =
                DriverManager.getConnection(url, username, password);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, qty);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Stock updated successfully!");

                String select = "SELECT * FROM Product WHERE product_id = ?";
                try (PreparedStatement ps2 = con.prepareStatement(select)) {
                    ps2.setInt(1, id);

                    try (ResultSet rs = ps2.executeQuery()) {
                        if (rs.next()) {
                            System.out.println("Product: "
                                    + rs.getString("product_name"));
                            System.out.println("Updated Quantity: "
                                    + rs.getInt("quantity"));
                        }
                    }
                }

            } else {
                System.out.println("Product does not exist!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

