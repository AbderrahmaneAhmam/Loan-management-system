package models;

public class MaterialModel {
    private int id;
    private String name;
    private String picture;

    public MaterialModel(int id, String name, String picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

}
