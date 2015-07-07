package gr.unipi.ergasia.controller.nav;

import gr.unipi.ergasia.lib.RequestUtilities;
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
                    homePage(request, response);
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
