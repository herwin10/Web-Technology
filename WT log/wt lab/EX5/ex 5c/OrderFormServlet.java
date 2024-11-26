import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/OrderFormServlet")
public class OrderFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String customerName = request.getParameter("customerName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String dishName = request.getParameter("dishName");
        String dishQuantity = request.getParameter("dishQuantity");
        String sessionTrackingMethod = request.getParameter("sessionTrackingMethod");

        // Process based on the selected session tracking method
        switch (sessionTrackingMethod) {
            case "cookies":
                handleCookiesSession(request, response, customerName, dishName, dishQuantity);
                break;
            case "hiddenFields":
                handleHiddenFieldsSession(request, response, customerName, dishName, dishQuantity);
                break;
            case "urlRewriting":
                handleUrlRewritingSession(request, response, customerName, dishName, dishQuantity);
                break;
            case "httpSession":
                handleHttpSession(request, response, customerName, dishName, dishQuantity);
                break;
            default:
                response.getWriter().println("<p>Invalid session tracking method selected. Please try again.</p>");
        }
    }

    // Method to handle Cookies session tracking
    private void handleCookiesSession(HttpServletRequest request, HttpServletResponse response, String customerName, String dishName, String dishQuantity) throws IOException {
        Cookie nameCookie = new Cookie("customerName", customerName);
        Cookie dishCookie = new Cookie("dishName", dishName);
        Cookie quantityCookie = new Cookie("dishQuantity", dishQuantity);

        response.addCookie(nameCookie);
        response.addCookie(dishCookie);
        response.addCookie(quantityCookie);

        response.sendRedirect("cookiesConfirmation");
    }

    // Method to handle Hidden Fields session tracking
    private void handleHiddenFieldsSession(HttpServletRequest request, HttpServletResponse response, String customerName, String dishName, String dishQuantity) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html><head><title>Hidden Fields Confirmation</title></head><body>");
        response.getWriter().println("<h2>Order Confirmation</h2>");
        response.getWriter().println("<form action='hiddenFieldsConfirmation' method='post'>");
        response.getWriter().println("<input type='hidden' name='customerName' value='" + customerName + "'>");
        response.getWriter().println("<input type='hidden' name='dishName' value='" + dishName + "'>");
        response.getWriter().println("<input type='hidden' name='dishQuantity' value='" + dishQuantity + "'>");
        response.getWriter().println("<input type='submit' value='Confirm Order'>");
        response.getWriter().println("</form>");
        response.getWriter().println("</body></html>");
    }

    // Method to handle URL Rewriting session tracking
    private void handleUrlRewritingSession(HttpServletRequest request, HttpServletResponse response, String customerName, String dishName, String dishQuantity) throws IOException {
        response.sendRedirect("urlRewritingConfirmation?customerName=" + customerName + "&dishName=" + dishName + "&dishQuantity=" + dishQuantity);
    }

    // Method to handle HttpSession session tracking
    private void handleHttpSession(HttpServletRequest request, HttpServletResponse response, String customerName, String dishName, String dishQuantity) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("customerName", customerName);
        session.setAttribute("dishName", dishName);
        session.setAttribute("dishQuantity", dishQuantity);

        response.sendRedirect("sessionConfirmation");
    }
}
