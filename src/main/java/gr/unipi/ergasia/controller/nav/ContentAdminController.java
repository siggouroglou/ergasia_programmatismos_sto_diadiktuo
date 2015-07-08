package gr.unipi.ergasia.controller.nav;

import gr.unipi.ergasia.lib.AuthedicationManager;
import gr.unipi.ergasia.lib.RequestUtilities;
import gr.unipi.ergasia.model.entity.ContentAdmin;
import gr.unipi.ergasia.model.entity.Film;
import gr.unipi.ergasia.model.entity.Provoli;
import gr.unipi.ergasia.service.FilmService;
import gr.unipi.ergasia.service.ProvoliService;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author siggouroglou
 */
public class ContentAdminController extends HttpServlet {

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
        ContentAdmin contentAdmin = AuthedicationManager.getInstance().getContentAdmin(request);
        if (contentAdmin == null) {
            response.sendRedirect(response.encodeRedirectURL("/web/signIn"));
            return;
        }
        request.setAttribute("contentAdmin", contentAdmin);

        // Catch a 5?? error.
        try {
            // Get the page name requested.
            String requestName = RequestUtilities.getRequestName(request, "contentAdmin/");
            switch (requestName) {
                case "home":
                    homePage(request, response);
                    break;
                case "exit":
                    exitPage(request, response, contentAdmin);
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

                case "provoli_list":
                    provoliList(request, response);
                    break;
                case "provoli_update":
                    provoliUpdate(request, response);
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
        request.getRequestDispatcher("/WEB-INF/views/contentAdmin/home.jsp").forward(request, response);
    }

    private void exitPage(HttpServletRequest request, HttpServletResponse response, ContentAdmin contentAdmin) throws ServletException, IOException {
        AuthedicationManager.getInstance().signOut(request, contentAdmin);
        response.sendRedirect(response.encodeRedirectURL("/web/home"));
    }

    //<editor-fold defaultstate="collapsed" desc="Film management">
    private void filmList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the film list.
        List<Film> filmList = FilmService.getInstance().readAll();
        request.setAttribute("filmList", filmList);

        // Continue with the view.
        request.getRequestDispatcher("/WEB-INF/views/contentAdmin/film_list.jsp").forward(request, response);
    }

    private void filmCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get variables.
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String description = request.getParameter("description");

        // Check if it is post.
        if (request.getMethod().equals("GET")) {
            request.getRequestDispatcher("/WEB-INF/views/contentAdmin/film_create.jsp").forward(request, response);
            return;
        }

        // Insert the row..
        Film film = new Film();
        film.setTitle(title);
        film.setCategory(category);
        film.setDescription(description);
        if (!FilmService.getInstance().insert(film)) {
            request.setAttribute("hasError", true);
            request.getRequestDispatcher("/WEB-INF/views/contentAdmin/film_create.jsp").forward(request, response);
            return;
        }

        // Redirect to film list.
        response.sendRedirect(response.encodeRedirectURL("/contentAdmin/film_list"));
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
            request.getRequestDispatcher("/WEB-INF/views/contentAdmin/film_update.jsp").forward(request, response);
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
            request.getRequestDispatcher("/WEB-INF/views/contentAdmin/film_update.jsp").forward(request, response);
            return;
        }

        // Redirect to film list.
        response.sendRedirect(response.encodeRedirectURL("/contentAdmin/film_list"));
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
            request.getRequestDispatcher("/WEB-INF/views/contentAdmin/film_delete.jsp").forward(request, response);
            return;
        }

        // Delete the row.
        if (!FilmService.getInstance().delete(filmId)) {
            request.setAttribute("hasError", true);
            request.getRequestDispatcher("/WEB-INF/views/contentAdmin/film_delete.jsp").forward(request, response);
            return;
        }

        // Redirect to film list.
        response.sendRedirect(response.encodeRedirectURL("/contentAdmin/film_list"));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Provoli management">
    private void provoliList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the film list.
        List<Provoli> provoliList = ProvoliService.getInstance().readAll();
        request.setAttribute("provoliList", provoliList);
        
        // Add the date format.
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        request.setAttribute("dateFormat", dateFormat);

        // Continue with the view.
        request.getRequestDispatcher("/WEB-INF/views/contentAdmin/provoli_list.jsp").forward(request, response);
    }

    private void provoliUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Add the date format.
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        request.setAttribute("dateFormat", dateFormat);
        
        // Get request id.
        int provoliId = -1;
        try {
            provoliId = Integer.parseInt(request.getParameter("provoli_id"));
        } catch (NumberFormatException ignorred) {
        }
        Provoli provoli = ProvoliService.getInstance().read(provoliId);
        request.setAttribute("provoli", provoli);

        // Check if it is post.
        if (request.getMethod().equals("GET")) {
            request.getRequestDispatcher("/WEB-INF/views/contentAdmin/provoli_update.jsp").forward(request, response);
            return;
        }

        // Get post parameters.
        int id = -1;
        int filmId = 0;
        int cinemaRoomId = 0;
        Date startDate = null;
        Date endDate = null;
        int numberOfReservations = 0;
        boolean available = false;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            filmId = Integer.parseInt(request.getParameter("filmId"));
            cinemaRoomId = Integer.parseInt(request.getParameter("cinemaRoomId"));
            startDate = dateFormat.parse(request.getParameter("startDate"));
            endDate = dateFormat.parse(request.getParameter("endDate"));
            numberOfReservations = Integer.parseInt(request.getParameter("numberOfReservations"));
            available = Boolean.parseBoolean(request.getParameter("available"));
        } catch (Exception ignorred) {
            request.setAttribute("hasError", true);
            request.getRequestDispatcher("/WEB-INF/views/contentAdmin/provoli_update.jsp").forward(request, response);
            return;
        }

        // Update the row..
        provoli.setId(id);
        provoli.setFilmId(filmId);
        provoli.setCinemaRoomId(cinemaRoomId);
        provoli.setStartDate(startDate);
        provoli.setEndDate(endDate);
        provoli.setNumberOfReservations(numberOfReservations);
        provoli.setAvailable(available);
        if (!ProvoliService.getInstance().update(provoli)) {
            request.setAttribute("hasError", true);
            request.getRequestDispatcher("/WEB-INF/views/contentAdmin/provoli_update.jsp").forward(request, response);
            return;
        }

        // Redirect to provoli list.
        response.sendRedirect(response.encodeRedirectURL("/contentAdmin/provoli_list"));
    }
    //</editor-fold>

}
