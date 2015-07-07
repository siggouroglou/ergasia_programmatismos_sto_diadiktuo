package gr.unipi.ergasia.model;

/**
 *
 * @author siggouroglou@gmail.com
 */
public class Admin {

    private String username;
    private String password;
    private String name;

    public Admin() {
        this.username = "";
        this.password = "";
        this.name = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
