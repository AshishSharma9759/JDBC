
import java.sql.*;
import java.util.Scanner;

public class ProductPriceUpdate {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/retail_db";
        String username = "root";
        String password = "975972";

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        System.out.print("Enter new price: ");
        double newPrice = sc.nextDouble();

        try (Connection con =
                DriverManager.getConnection(url, username, password)) {

            // Search product
            String search = "SELECT * FROM Product WHERE product_id = ?";

            try (PreparedStatement ps = con.prepareStatement(search)) {

                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {

                        if (newPrice > 0) {

                            String update =
                                "UPDATE Product SET price = ? WHERE product_id = ?";

                            try (PreparedStatement ps2 =
                                    con.prepareStatement(update)) {

                                ps2.setDouble(1, newPrice);
                                ps2.setInt(2, id);

                                ps2.executeUpdate();
                            }

                            System.out.println("Price updated successfully!");

                            // Display updated details
                            try (PreparedStatement ps3 =
                                    con.prepareStatement(search)) {

                                ps3.setInt(1, id);

                                try (ResultSet rs2 = ps3.executeQuery()) {

                                    if (rs2.next()) {
                                        System.out.println("Product ID: "
                                                + rs2.getInt("product_id"));
                                        System.out.println("Product Name: "
                                                + rs2.getString("product_name"));
                                        System.out.println("Category: "
                                                + rs2.getString("category"));
                                        System.out.println("Quantity: "
                                                + rs2.getInt("quantity"));
                                        System.out.println("New Price: "
                                                + rs2.getDouble("price"));
                                    }
                                }
                            }

                        } else {
                            System.out.println("Price must be greater than zero!");
                        }

                    } else {
                        System.out.println("Product does not exist!");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

