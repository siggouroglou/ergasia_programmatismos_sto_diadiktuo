package gr.unipi.ergasia.controller.nav;

import gr.unipi.ergasia.lib.AuthedicationManager;
import gr.unipi.ergasia.lib.RequestUtilities;
import gr.unipi.ergasia.model.entity.Customer;
import gr.unipi.ergasia.model.entity.Provoli;
import gr.unipi.ergasia.model.entity.Reservation;
import gr.unipi.ergasia.service.ProvoliService;
import gr.unipi.ergasia.service.ReservationService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author siggouroglou
 */
public class CustomerController extends HttpServlet {

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

        // Validate the user if he is an contentAdmin.
        Customer customer = AuthedicationManager.getInstance().getContentCustomer(request);
        if (customer == null) {
            response.sendRedirect(response.encodeRedirectURL("/web/signIn"));
            return;
        }
        request.setAttribute("customer", customer);
        request.setAttribute("pageName", "signIn");
        

        // Catch a 5?? error.
        try {
            // Get the page name requested.
            String requestName = RequestUtilities.getRequestName(request, "customer/");
            switch (requestName) {
                case "home":
                    homePage(request, response);
                    break;
                case "makeReservation":
                    makeReservation(request, response, customer);
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
        request.getRequestDispatcher("/WEB-INF/views/customer/home.jsp").forward(request, response);
    }

    private void makeReservation(HttpServletRequest request, HttpServletResponse response, Customer customer) throws ServletException, IOException {
        // Get the provoli object.
        int provoliId = -1;
        try {
            provoliId = Integer.parseInt(request.getParameter("provoli_id"));
        } catch (NumberFormatException ignorred) {
        }
        Provoli provoli = ProvoliService.getInstance().read(provoliId);

        // Check if it is valid.
        if (provoli == null) {
            request.getRequestDispatcher("/WEB-INF/views/customer/makeReservation_error.jsp").forward(request, response);
            return;
        }

        // Check if this reservation is available.
        if (!provoli.isAvailable()) {
            request.getRequestDispatcher("/WEB-INF/views/customer/makeReservation_error.jsp").forward(request, response);
            return;
        }

        // Count how many reservations are existing.
        int reservationsCount = ReservationService.getInstance().countWithProvoliId(provoliId);

        // Check this reservations availability and insert this reservation.
        if (reservationsCount < provoli.getNumberOfReservations()) {
            Reservation reservation = new Reservation();
            reservation.setCustomerUsername(customer.getUsername());
            reservation.setProvoliId(provoliId);
            if (!ReservationService.getInstance().insert(reservation)) {
                request.getRequestDispatcher("/WEB-INF/views/customer/makeReservation_error.jsp").forward(request, response);
                return;
            }
            reservationsCount++;
        }
        
        // Check if this provoli is full and update the available flag.
        if(reservationsCount == provoli.getNumberOfReservations()) {
            provoli.setAvailable(false);
            ProvoliService.getInstance().update(provoli);
        }

        request.getRequestDispatcher("/WEB-INF/views/customer/makeReservation_success.jsp").forward(request, response);
    }

}
