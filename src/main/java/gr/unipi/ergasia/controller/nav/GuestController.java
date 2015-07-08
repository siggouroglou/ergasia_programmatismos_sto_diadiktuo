package gr.unipi.ergasia.controller.nav;

import gr.unipi.ergasia.lib.AuthedicationManager;
import gr.unipi.ergasia.lib.RequestUtilities;
import gr.unipi.ergasia.model.entity.Admin;
import gr.unipi.ergasia.model.entity.ContentAdmin;
import gr.unipi.ergasia.model.entity.Customer;
import gr.unipi.ergasia.model.entity.UserRole;
import gr.unipi.ergasia.service.AdminService;
import gr.unipi.ergasia.service.ContentAdminService;
import gr.unipi.ergasia.service.CustomerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author siggouroglou
 */
public class GuestController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set default encoding.
        response.setContentType("text/html;charset=UTF-8");

        // Catch a 5?? error.
        try {
            // Get the page name requested.
            String requestName = RequestUtilities.getRequestName(request, "web/");
            switch (requestName) {
                case "home":
                    homePage(request, response);
                    break;
                case "signIn":
                    signIn(request, response);
                    break;
                case "register":
                    register(request, response);
                    break;
                case "search-movie":
                    searchMovie(request, response);
                    break;
                case "movieList":
                    movieList(request, response);
                    break;
                case "makeReservation":
                    makeReservation(request, response);
                    break;
                default:
                    request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);

            }
        } catch (Exception ex) {
            request.getRequestDispatcher("/WEB-INF/views/error/internal.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void homePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/guest/home.jsp").forward(request, response);
    }

    private void signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if this is a post request.
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Check the role attribute.
        if (role == null) {
            request.getRequestDispatcher("/WEB-INF/views/guest/signIn.jsp").forward(request, response);
        } else {
            // Initialize variabbles in case of error.
            boolean hasError = true;

            // For each role validate username/password and log them in.
            switch (role) {
                case "customer":
                    if (AuthedicationManager.getInstance().isAuthedicated(username, password, UserRole.CUSTOMER)) {
                        Customer customer = CustomerService.getInstance().read(username);
                        AuthedicationManager.getInstance().signIn(request, customer);
                        request.setAttribute("name", customer.getName());
                        response.sendRedirect(response.encodeRedirectURL("/customer/home"));
                        hasError = false;
                    }
                    break;
                case "contentAdmin":
                    if (AuthedicationManager.getInstance().isAuthedicated(username, password, UserRole.CONTENT_ADMIN)) {
                        ContentAdmin contentAdmin = ContentAdminService.getInstance().read(username);
                        AuthedicationManager.getInstance().signIn(request, contentAdmin);
                        response.sendRedirect(response.encodeRedirectURL("/contentAdmin/home"));
                        hasError = false;
                    }
                    break;
                case "admin":
                    if (AuthedicationManager.getInstance().isAuthedicated(username, password, UserRole.ADMIN)) {
                        Admin admin = AdminService.getInstance().read(username);
                        AuthedicationManager.getInstance().signIn(request, admin);
                        response.sendRedirect(response.encodeRedirectURL("/admin/home"));
                        hasError = false;
                    }
                    break;
                default:
                    throw new RuntimeException("Sign in role not valid.");
            }

            // Redirect to the correct page.
            if (hasError) {
                request.setAttribute("hasError", hasError);
                request.getRequestDispatcher("/WEB-INF/views/guest/signIn.jsp").forward(request, response);
            }
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if this is a post request.
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");

        // Check the role attribute.
        if (username == null) {
            request.getRequestDispatcher("/WEB-INF/views/guest/register.jsp").forward(request, response);
        } else {
            // Create the customer.
            Customer customer = new Customer();
            customer.setUsername(username);
            customer.setPassword(password);
            customer.setName(name);

            // Insert the customer.
            request.setAttribute("hasError", !CustomerService.getInstance().insert(customer));

            // Redirect to the correct page.
            request.getRequestDispatcher("/WEB-INF/views/guest/register_answer.jsp").forward(request, response);
        }
    }

    private void searchMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/guest/home.jsp").forward(request, response);
    }

    private void movieList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/guest/home.jsp").forward(request, response);
    }

    private void makeReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/guest/home.jsp").forward(request, response);
    }

}
