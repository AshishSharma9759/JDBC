
import java.sql.*;
import java.util.Scanner;

public class ProductRetrieval {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/retail_db";
        String username = "root";
        String password = "975972";

        String query = "SELECT * FROM Product "
                + "WHERE category = ? AND quantity < 20 "
                + "ORDER BY price DESC";

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        try (Connection con =
                DriverManager.getConnection(url, username, password);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, category);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("ID: " + rs.getInt("product_id"));
                System.out.println("Name: " + rs.getString("product_name"));
                System.out.println("Category: " + rs.getString("category"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println("Price: " + rs.getDouble("price"));

                System.out.println("----------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
