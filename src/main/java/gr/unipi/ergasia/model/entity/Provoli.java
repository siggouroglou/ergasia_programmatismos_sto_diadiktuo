package gr.unipi.ergasia.model.entity;

import java.sql.Date;

/**
 *
 * @author siggouroglou@gmail.com
 */
public class Provoli {

    private Integer id;
    private int filmId;
    private int cinemaRoomId;
    private Date startDate;
    private Date endDate;
    private int numberOfReservations;
    private boolean available;

    public Provoli() {
        this.id = null;
        this.filmId = -1;
        this.cinemaRoomId = -1;
        this.startDate = new Date(0);
        this.endDate = new Date(0);
        this.numberOfReservations = 0;
        this.available = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getCinemaRoomId() {
        return cinemaRoomId;
    }

    public void setCinemaRoomId(int cinemaRoomId) {
        this.cinemaRoomId = cinemaRoomId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfReservations() {
        return numberOfReservations;
    }

    public void setNumberOfReservations(int numberOfReservations) {
        this.numberOfReservations = numberOfReservations;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
