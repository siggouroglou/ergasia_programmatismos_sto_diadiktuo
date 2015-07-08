package gr.unipi.ergasia.controller.nav;

import gr.unipi.ergasia.lib.AuthedicationManager;
import gr.unipi.ergasia.lib.RequestUtilities;
import gr.unipi.ergasia.model.entity.Admin;
import gr.unipi.ergasia.model.entity.ContentAdmin;
import gr.unipi.ergasia.model.entity.Customer;
import gr.unipi.ergasia.model.entity.Film;
import gr.unipi.ergasia.model.entity.UserRole;
import gr.unipi.ergasia.service.AdminService;
import gr.unipi.ergasia.service.ContentAdminService;
import gr.unipi.ergasia.service.CustomerService;
import gr.unipi.ergasia.service.FilmService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author siggouroglou
 */
public class AdminController extends HttpServlet {

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

        // Validate the user if he is an admin.
        Admin admin = AuthedicationManager.getInstance().getAdmin(request);
        if (admin == null) {
            response.sendRedirect(response.encodeRedirectURL("/web/signIn"));
            return;
        }
        request.setAttribute("admin", admin);

        // Catch a 5?? error.
        try {
            // Get the page name requested.
            String requestName = RequestUtilities.getRequestName(request, "admin/");
            switch (requestName) {
                case "home":
                    homePage(request, response);
                    break;
                case "film_list":
                    filmList(request, response);
                    break;
                case "film_create":
                    filmCreate(request, response);
                    break;
                case "film_update":
                    filmUpdate(request, response);
                    break;
                case "film_delete":
                    filmDelete(request, response);
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
        request.getRequestDispatcher("/WEB-INF/views/admin/home.jsp").forward(request, response);
    }

    private void filmList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the film list.
        List<Film> filmList = FilmService.getInstance().readAll();
        request.setAttribute("filmList", filmList);

        // Continue with the view.
        request.getRequestDispatcher("/WEB-INF/views/admin/film_list.jsp").forward(request, response);
    }

    private void filmCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get variables.
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String description = request.getParameter("description");

        // Check if it is post.
        if (request.getMethod().equals("GET")) {
            request.getRequestDispatcher("/WEB-INF/views/admin/film_create.jsp").forward(request, response);
            return;
        }

        // Insert the row..
        Film film = new Film();
        film.setTitle(title);
        film.setCategory(category);
        film.setDescription(description);
        if (!FilmService.getInstance().insert(film)) {
            request.setAttribute("hasError", true);
            request.getRequestDispatcher("/WEB-INF/views/admin/film_create.jsp").forward(request, response);
            return;
        }

        // Redirect to film list.
        response.sendRedirect(response.encodeRedirectURL("/admin/film_list"));
    }

    private void filmUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get request id.
        int filmId = -1;
        try {
            filmId = Integer.parseInt(request.getParameter("film_id"));
        } catch (NumberFormatException ignorred) {
        }
        Film film = FilmService.getInstance().read(filmId);
        request.setAttribute("film", film);

        // Check if it is post.
        if (request.getMethod().equals("GET")) {
            request.getRequestDispatcher("/WEB-INF/views/admin/film_update.jsp").forward(request, response);
            return;
        }

        // Get post parameters.
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException ignorred) {
        }
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String description = request.getParameter("description");

        // Update the row..
        film.setId(id);
        film.setTitle(title);
        film.setCategory(category);
        film.setDescription(description);
        if (!FilmService.getInstance().update(film)) {
            request.setAttribute("hasError", true);
            request.getRequestDispatcher("/WEB-INF/views/admin/film_update.jsp").forward(request, response);
            return;
        }

        // Redirect to film list.
        response.sendRedirect(response.encodeRedirectURL("/admin/film_list"));
    }

    private void filmDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get request id.
        int filmId = -1;
        try {
            filmId = Integer.parseInt(request.getParameter("film_id"));
        } catch (NumberFormatException ignorred) {
        }
        Film film = FilmService.getInstance().read(filmId);
        if (film == null) {
            request.getRequestDispatcher("/WEB-INF/views/admin/film_delete.jsp").forward(request, response);
            return;
        }

        // Delete the row.
        if (!FilmService.getInstance().delete(filmId)) {
            request.setAttribute("hasError", true);
            request.getRequestDispatcher("/WEB-INF/views/admin/film_delete.jsp").forward(request, response);
            return;
        }

        // Redirect to film list.
        response.sendRedirect(response.encodeRedirectURL("/admin/film_list"));
    }

}
