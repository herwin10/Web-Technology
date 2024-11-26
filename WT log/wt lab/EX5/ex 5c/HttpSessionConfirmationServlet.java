import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sessionConfirmation")
public class HttpSessionConfirmationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve session attributes
        HttpSession session = request.getSession();
        String customerName = (String) session.getAttribute("customerName");
        String dishName = (String) session.getAttribute("dishName");
        String dishQuantity = (String) session.getAttribute("dishQuantity");

        // Generate the response
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html><head><title>Order Confirmation</title></head><body>");
        response.getWriter().println("<h1>Order Confirmation</h1>");

        if (customerName != null && dishName != null && dishQuantity != null) {
            response.getWriter().println("<p>Thank you, <b>" + customerName + "</b>!</p>");
            response.getWriter().println("<p>Your order for <b>" + dishQuantity + "</b> portion(s) of <b>" + dishName + "</b> has been successfully placed.</p>");
        } else {
            response.getWriter().println("<p>Sorry, we couldn't retrieve your order details. Please try again.</p>");
        }

        response.getWriter().println("<a href='menu.html'>Back to Menu</a>");
        response.getWriter().println("</body></html>");
    }
}
