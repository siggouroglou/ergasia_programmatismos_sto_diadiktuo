package gr.unipi.ergasia.model.entity;

/**
 *
 * @author siggouroglou@gmail.com
 */
public class Reservation {

    private String customerUsername;
    private int provoliId;

    public Reservation() {
        this.customerUsername = "";
        this.provoliId = -1;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public int getProvoliId() {
        return provoliId;
    }

    public void setProvoliId(int provoliId) {
        this.provoliId = provoliId;
    }
}
