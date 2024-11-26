import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class OrderServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Retrieve form data
        int userId = Integer.parseInt(request.getParameter("user_id"));
        String orderDetails = request.getParameter("order_details");
        double totalPrice = Double.parseDouble(request.getParameter("total_price"));

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Load MySQL driver and establish connection
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_db", "root", "");

            // Insert order into the database
            String sql = "INSERT INTO Orders (user_id, order_details, total_price, order_date) VALUES (?, ?, ?, NOW())";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, orderDetails);
            stmt.setDouble(3, totalPrice);

            int rowsInserted = stmt.executeUpdate();
            
            // Display success or error message
            out.println("<html><body style='font-family: Arial, sans-serif; text-align: center; margin-top: 50px;'>");
            if (rowsInserted > 0) {
                out.println("<h1 style='color: #007bff;'>Order Placed Successfully!</h1>");
                out.println("<p>Thank you for choosing Global Flavors Restaurant. Your order details:</p>");
                out.println("<p><strong>Order ID:</strong> " + userId + "</p>");
                out.println("<p><strong>Details:</strong> " + orderDetails + "</p>");
                out.println("<p><strong>Total Price:</strong> $" + totalPrice + "</p>");
                out.println("<a href='menu.html' style='text-decoration: none; color: white; background-color: #007bff; padding: 10px 20px; border-radius: 5px;'>Order More</a>");
            } else {
                out.println("<h1 style='color: #ff0000;'>Order Failed!</h1>");
                out.println("<p>We encountered an issue while placing your order. Please try again.</p>");
            }
            out.println("</body></html>");

        } catch (Exception e) {
            // Handle database errors
            out.println("<html><body style='font-family: Arial, sans-serif; text-align: center; margin-top: 50px;'>");
            out.println("<h1 style='color: #ff0000;'>Error!</h1>");
            out.println("<p>We encountered an error: " + e.getMessage() + "</p>");
            out.println("<p>Please try again later.</p>");
            out.println("</body></html>");
        } finally {
            // Close resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                out.println("<html><body style='font-family: Arial, sans-serif; text-align: center; margin-top: 50px;'>");
                out.println("<h1 style='color: #ff0000;'>Resource Closure Error</h1>");
                out.println("<p>Failed to close database resources: " + e.getMessage() + "</p>");
                out.println("</body></html>");
            }
        }
    }
}
