import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hiddenFieldsConfirmation")
public class HiddenFieldsConfirmationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve hidden field values from the request
        String customerName = request.getParameter("customerName");
        String dishName = request.getParameter("dishName");
        String dishQuantity = request.getParameter("dishQuantity");

        // Generate the response page
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html><head><title>Order Confirmation</title></head><body>");
        response.getWriter().println("<h1>Order Confirmation</h1>");
        if (customerName != null && dishName != null && dishQuantity != null) {
            response.getWriter().println("<p>Thank you, <b>" + customerName + "</b>!</p>");
            response.getWriter().println("<p>Your order for <b>" + dishQuantity + "</b> portion(s) of <b>" + dishName + "</b> has been successfully placed.</p>");
        } else {
            response.getWriter().println("<p>Oops! We couldn't retrieve your order details. Please try again.</p>");
        }
        response.getWriter().println("<a href='menu.html'>Back to Menu</a>");
        response.getWriter().println("</body></html>");
    }
}
