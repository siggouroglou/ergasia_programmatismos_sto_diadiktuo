package gr.unipi.ergasia.model.entity;

/**
 *
 * @author siggouroglou@gmail.com
 */
public class Film {

    private Integer id;
    private String title;
    private String category;
    private String description;

    public Film() {
        this.id = null;
        this.title = "";
        this.category = "";
        this.description = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
