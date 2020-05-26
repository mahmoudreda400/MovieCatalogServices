package edu.miu.moviecatalogservice.models;

public class CatalogItem {

    private long id;
    private String name;
    private String desc;
    private int rating;

//    public CatalogItem(){
//
//    }
    public CatalogItem(long id, String name, String desc, int rating) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
