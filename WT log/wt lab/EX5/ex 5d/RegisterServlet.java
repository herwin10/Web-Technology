import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve registration data from the form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Load MySQL JDBC Driver and connect to the database
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_db", "root", "");

            // Insert user data into the Users table
            String sql = "INSERT INTO Users (name, email, password) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);

            int rowsInserted = stmt.executeUpdate();

            // Generate HTML response
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body style='font-family: Arial, sans-serif; text-align: center; margin-top: 50px;'>");

            if (rowsInserted > 0) {
                out.println("<h1 style='color: #007bff;'>Registration Successful!</h1>");
                out.println("<p>Welcome to Global Flavors Restaurant, <strong>" + name + "</strong>!</p>");
                out.println("<p>You can now log in to explore our menu and place your orders.</p>");
                out.println("<a href='login.html' style='text-decoration: none; color: white; background-color: #007bff; padding: 10px 20px; border-radius: 5px;'>Login</a>");
            } else {
                out.println("<h1 style='color: #ff0000;'>Registration Failed</h1>");
                out.println("<p>We couldn't complete your registration. Please try again later.</p>");
            }

            out.println("</body></html>");
        } catch (SQLException e) {
            // Handle database errors
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body style='font-family: Arial, sans-serif; text-align: center; margin-top: 50px;'>");
            out.println("<h1 style='color: #ff0000;'>Database Error</h1>");
            out.println("<p>We encountered a problem while processing your registration: " + e.getMessage() + "</p>");
            out.println("<p>Please try again later.</p>");
            out.println("</body></html>");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // Handle JDBC driver not found error
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body style='font-family: Arial, sans-serif; text-align: center; margin-top: 50px;'>");
            out.println("<h1 style='color: #ff0000;'>System Error</h1>");
            out.println("<p>We couldn't locate the database driver. Please contact support.</p>");
            out.println("</body></html>");
            e.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
