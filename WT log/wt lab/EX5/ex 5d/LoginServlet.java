import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_db", "root", "");

            String query = "SELECT * FROM Users WHERE email = ? AND password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Redirect to the main menu page after a successful login
                response.sendRedirect("menu.html"); 
            } else {
                // Redirect back to the login page with an error message
                response.sendRedirect("login.html?error=Invalid login credentials. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Redirect to an error page with a database connection issue message
            response.sendRedirect("login.html?error=Unable to connect to the database. Please try again later.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Redirect to an error page with a JDBC driver issue message
            response.sendRedirect("login.html?error=Driver not found. Please contact support.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
