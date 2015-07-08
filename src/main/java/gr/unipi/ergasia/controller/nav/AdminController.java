package gr.unipi.ergasia.controller.nav;

import gr.unipi.ergasia.lib.AuthedicationManager;
import gr.unipi.ergasia.lib.RequestUtilities;
import gr.unipi.ergasia.lib.security.Encryption;
import gr.unipi.ergasia.model.entity.Admin;
import gr.unipi.ergasia.model.entity.CinemaRoom;
import gr.unipi.ergasia.model.entity.ContentAdmin;
import gr.unipi.ergasia.model.entity.Film;
import gr.unipi.ergasia.service.CinemaRoomService;
import gr.unipi.ergasia.service.ContentAdminService;
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

                case "cinemaRoom_list":
                    cinemaRoomList(request, response);
                    break;
                case "cinemaRoom_create":
                    cinemaRoomCreate(request, response);
                    break;
                case "cinemaRoom_update":
                    cinemaRoomUpdate(request, response);
                    break;
                case "cinemaRoom_delete":
                    cinemaRoomDelete(request, response);
                    break;

                case "contentAdmin_list":
                    contentAdminList(request, response);
                    break;
                case "contentAdmin_create":
                    contentAdminCreate(request, response);
                    break;
                case "contentAdmin_updateInfo":
                    contentAdminUpdateInfo(request, response);
                    break;
                case "contentAdmin_updatePass":
                    contentAdminUpdatePass(request, response);
                    break;
                case "contentAdmin_delete":
                    contentAdminDelete(request, response);
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

    //<editor-fold defaultstate="collapsed" desc="Film management">
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CinemaRoom management">
    private void cinemaRoomList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the film list.
        List<CinemaRoom> cinemaRoomList = CinemaRoomService.getInstance().readAll();
        request.setAttribute("cinemaRoomList", cinemaRoomList);

        // Continue with the view.
        request.getRequestDispatcher("/WEB-INF/views/admin/cinemaRoom_list.jsp").forward(request, response);
    }

    private void cinemaRoomCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if it is post.
        if (request.getMethod().equals("GET")) {
            request.getRequestDispatcher("/WEB-INF/views/admin/cinemaRoom_create.jsp").forward(request, response);
            return;
        }

        // Post variables.
        String title = request.getParameter("title");
        boolean support3D = false;
        int totalSeats = 0;
        try {
            support3D = Boolean.parseBoolean(request.getParameter("support3D"));
            totalSeats = Integer.parseInt(request.getParameter("totalSeats"));
        } catch (Exception ignorred) {
        }

        // Insert the row..
        CinemaRoom cinemaRoom = new CinemaRoom();
        cinemaRoom.setTitle(title);
        cinemaRoom.setSupport3D(support3D);
        cinemaRoom.setTotalSeats(totalSeats);
        if (!CinemaRoomService.getInstance().insert(cinemaRoom)) {
            request.setAttribute("hasError", true);
            request.getRequestDispatcher("/WEB-INF/views/admin/cinemaRoom_create.jsp").forward(request, response);
            return;
        }

        // Redirect to cinemaRoom list.
        response.sendRedirect(response.encodeRedirectURL("/admin/cinemaRoom_list"));
    }

    private void cinemaRoomUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get request id.
        int cinemaRoomId = -1;
        try {
            cinemaRoomId = Integer.parseInt(request.getParameter("cinemaRoom_id"));
        } catch (NumberFormatException ignorred) {
        }
        CinemaRoom cinemaRoom = CinemaRoomService.getInstance().read(cinemaRoomId);
        request.setAttribute("cinemaRoom", cinemaRoom);

        // Check if it is post.
        if (request.getMethod().equals("GET")) {
            request.getRequestDispatcher("/WEB-INF/views/admin/cinemaRoom_update.jsp").forward(request, response);
            return;
        }

        // Get post parameters.
        String title = request.getParameter("title");
        int id = -1;
        boolean support3D = false;
        int totalSeats = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            support3D = Boolean.parseBoolean(request.getParameter("support3D"));
            totalSeats = Integer.parseInt(request.getParameter("totalSeats"));
        } catch (Exception ignorred) {
        }

        // Update the row..
        cinemaRoom.setId(id);
        cinemaRoom.setTitle(title);
        cinemaRoom.setSupport3D(support3D);
        cinemaRoom.setTotalSeats(totalSeats);
        if (!CinemaRoomService.getInstance().update(cinemaRoom)) {
            request.setAttribute("hasError", true);
            request.getRequestDispatcher("/WEB-INF/views/admin/cinemaRoom_update.jsp").forward(request, response);
            return;
        }

        // Redirect to cinemaRoom list.
        response.sendRedirect(response.encodeRedirectURL("/admin/cinemaRoom_list"));
    }

    private void cinemaRoomDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get request id.
        int cinemaRoomId = -1;
        try {
            cinemaRoomId = Integer.parseInt(request.getParameter("cinemaRoom_id"));
        } catch (NumberFormatException ignorred) {
        }
        CinemaRoom cinemaRoom = CinemaRoomService.getInstance().read(cinemaRoomId);
        if (cinemaRoom == null) {
            request.getRequestDispatcher("/WEB-INF/views/admin/cinemaRoom_delete.jsp").forward(request, response);
            return;
        }

        // Delete the row.
        if (!CinemaRoomService.getInstance().delete(cinemaRoomId)) {
            request.setAttribute("hasError", true);
            request.getRequestDispatcher("/WEB-INF/views/admin/cinemaRoom_delete.jsp").forward(request, response);
            return;
        }

        // Redirect to cinemaRoom list.
        response.sendRedirect(response.encodeRedirectURL("/admin/cinemaRoom_list"));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ContentAdmin management">
    private void contentAdminList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the film list.
        List<ContentAdmin> contentAdminList = ContentAdminService.getInstance().readAll();
        request.setAttribute("contentAdminList", contentAdminList);

        // Continue with the view.
        request.getRequestDispatcher("/WEB-INF/views/admin/contentAdmin_list.jsp").forward(request, response);
    }

    private void contentAdminCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if it is post.
        if (request.getMethod().equals("GET")) {
            request.getRequestDispatcher("/WEB-INF/views/admin/contentAdmin_create.jsp").forward(request, response);
            return;
        }

        // Post variables.
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");
        String name = request.getParameter("name");
        
        // Validate.
        if(!password.equals(passwordRepeat)){
            request.setAttribute("hasError", true);
            request.getRequestDispatcher("/WEB-INF/views/admin/contentAdmin_create.jsp").forward(request, response);
            return;
        }

        // Insert the row..
        ContentAdmin contentAdmin = new ContentAdmin();
        contentAdmin.setUsername(username);
        contentAdmin.setPassword(Encryption.getHashMD5(password));
        contentAdmin.setName(name);
        if (!ContentAdminService.getInstance().insert(contentAdmin)) {
            request.setAttribute("hasError", true);
            request.getRequestDispatcher("/WEB-INF/views/admin/contentAdmin_create.jsp").forward(request, response);
            return;
        }

        // Redirect to contentAdmin list.
        response.sendRedirect(response.encodeRedirectURL("/admin/contentAdmin_list"));
    }

    private void contentAdminUpdateInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get request id.
        String contentAdminUsername = request.getParameter("contentAdmin_username");
        ContentAdmin contentAdmin = ContentAdminService.getInstance().read(contentAdminUsername);
        request.setAttribute("contentAdmin", contentAdmin);

        // Check if it is post.
        if (request.getMethod().equals("GET")) {
            request.getRequestDispatcher("/WEB-INF/views/admin/contentAdmin_updateInfo.jsp").forward(request, response);
            return;
        }

        // Get post parameters.
        String usernameOld = request.getParameter("usernameOld");
        String username = request.getParameter("username");
        String name = request.getParameter("name");

        // Update the row..
        contentAdmin = ContentAdminService.getInstance().read(usernameOld);
        contentAdmin.setUsername(username);
        contentAdmin.setName(name);
        if (!ContentAdminService.getInstance().update(usernameOld, contentAdmin)) {
            request.setAttribute("hasError", true);
            request.getRequestDispatcher("/WEB-INF/views/admin/contentAdmin_updateInfo.jsp").forward(request, response);
            return;
        }

        // Redirect to contentAdmin list.
        request.setAttribute("contentAdmin", contentAdmin);
        response.sendRedirect(response.encodeRedirectURL("/admin/contentAdmin_list"));
    }

    private void contentAdminUpdatePass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get request id.
        String contentAdminUsername = request.getParameter("contentAdmin_username");
        ContentAdmin contentAdmin = ContentAdminService.getInstance().read(contentAdminUsername);
        request.setAttribute("contentAdmin", contentAdmin);

        // Check if it is post.
        if (request.getMethod().equals("GET")) {
            request.getRequestDispatcher("/WEB-INF/views/admin/contentAdmin_updatePass.jsp").forward(request, response);
            return;
        }

        // Get post parameters.
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");
        
        // Validate.
        if(!password.equals(passwordRepeat)){
            request.setAttribute("hasError", true);
            request.getRequestDispatcher("/WEB-INF/views/admin/contentAdmin_updatePass.jsp").forward(request, response);
            return;
        }

        // Update the row..
        contentAdmin = ContentAdminService.getInstance().read(username);
        contentAdmin.setPassword(Encryption.getHashMD5(password));
        if (!ContentAdminService.getInstance().update(contentAdmin)) {
            request.setAttribute("hasError", true);
            request.getRequestDispatcher("/WEB-INF/views/admin/contentAdmin_updatePass.jsp").forward(request, response);
            return;
        }

        // Redirect to contentAdmin list.
        request.setAttribute("contentAdmin", contentAdmin);
        response.sendRedirect(response.encodeRedirectURL("/admin/contentAdmin_list"));
    }

    private void contentAdminDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get request id.
        String contentAdminUsername = request.getParameter("contentAdmin_username");

        // Delete the row.
        if (!ContentAdminService.getInstance().delete(contentAdminUsername)) {
            request.setAttribute("hasError", true);
            request.getRequestDispatcher("/WEB-INF/views/admin/contentAdmin_delete.jsp").forward(request, response);
            return;
        }

        // Redirect to contentAdmin list.
        response.sendRedirect(response.encodeRedirectURL("/admin/contentAdmin_list"));
    }
    //</editor-fold>

}
