import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/urlRewritingConfirmation")
public class URLRewritingConfirmationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the URL
        String customerName = request.getParameter("customerName");
        String dishName = request.getParameter("dishName");
        String dishQuantity = request.getParameter("dishQuantity");

        // Generate the response
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html><head><title>Order Confirmation</title></head><body>");
        response.getWriter().println("<h1>Thank you for your order, " + customerName + "!</h1>");
        response.getWriter().println("<p>Your order for <strong>" + dishQuantity + " " + dishName + "</strong> has been successfully placed using <em>URL Rewriting</em>.</p>");
        response.getWriter().println("<p>We hope you enjoy your meal. Your delicious food will be delivered shortly!</p>");
        response.getWriter().println("<a href='menu.html'>Back to Menu</a>");
        response.getWriter().println("</body></html>");
    }
}
