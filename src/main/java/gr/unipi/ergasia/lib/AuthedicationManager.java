package gr.unipi.ergasia.lib;

import gr.unipi.ergasia.lib.security.Encryption;
import gr.unipi.ergasia.model.entity.Admin;
import gr.unipi.ergasia.model.other.AuthedicatedUser;
import gr.unipi.ergasia.model.entity.ContentAdmin;
import gr.unipi.ergasia.model.entity.Customer;
import gr.unipi.ergasia.model.other.UserRole;
import gr.unipi.ergasia.service.AdminService;
import gr.unipi.ergasia.service.ContentAdminService;
import gr.unipi.ergasia.service.CustomerService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author siggouroglou@gmail.com
 */
public class AuthedicationManager {

    private static AuthedicationManager INSTANCE;

    private AuthedicationManager() {

    }

    public static AuthedicationManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AuthedicationManager();
        }
        return INSTANCE;
    }

    public boolean isAuthedicated(String username, String password, UserRole role) {
        boolean isAuthedicated = false;

        switch (role) {
            case CUSTOMER:
                isAuthedicated = CustomerService.getInstance().isAuthedicated(username, Encryption.getHashMD5(password));
                break;
            case CONTENT_ADMIN:
                isAuthedicated = ContentAdminService.getInstance().isAuthedicated(username, Encryption.getHashMD5(password));
                break;
            case ADMIN:
                isAuthedicated = AdminService.getInstance().isAuthedicated(username, Encryption.getHashMD5(password));
                break;
        }

        return isAuthedicated;
    }

    public void signIn(HttpServletRequest request, AuthedicatedUser user) {
        HttpSession session = request.getSession();
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());
    }
    
    public void signOut(HttpServletRequest request, AuthedicatedUser user) {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.removeAttribute("role");
    }

    public Admin getAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object username = session.getAttribute("username");
        if(username == null || !(username instanceof String)){
            return null;
        }
        
        return AdminService.getInstance().read((String) username);
    }

    public ContentAdmin getContentAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object username = session.getAttribute("username");
        if(username == null || !(username instanceof String)){
            return null;
        }
        
        return ContentAdminService.getInstance().read((String) username);
    }

    public Customer getContentCustomer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object username = session.getAttribute("username");
        if(username == null || !(username instanceof String)){
            return null;
        }
        
        return CustomerService.getInstance().read((String) username);
    }
}
