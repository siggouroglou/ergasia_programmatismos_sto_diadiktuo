package gr.unipi.ergasia.model;

/**
 *
 * @author siggouroglou@gmail.com
 */
public class CinemaRoom {

    private Integer id;
    private boolean support3D;
    private int totalSeats;

    public CinemaRoom() {
        this.id = null;
        this.support3D = false;
        this.totalSeats = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isSupport3D() {
        return support3D;
    }

    public void setSupport3D(boolean support3D) {
        this.support3D = support3D;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

}
