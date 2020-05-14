package com.codeup.springblogapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ads")
public class Ad {

    //create primary key for the "ads" table in db, and auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    String title;

    @Column
    String description;

    // many ads can only have on author/owner
    // @Many[ads]toOne[user]
    @ManyToOne
    private User user; //Spring knows ad will be attached to User

    // each ad can have many images
    // one ad can have many images
    // here(ad) --> there(images)
    // @One[ad]toMany[images]
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ad") //mappedBy is so because we're in Ad class
    private List<AdImage> images;  //list of images that each ad will have

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            // name of the intermediate table
            name = "ads_categories",
            // name for the id of this model joinColumns
            joinColumns = {@JoinColumn(name = "ad_id")},
            // name for the id of the related(other) model inverseJoinColumns
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> categories;

    public Ad(){
    }

    public Ad(long id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Ad(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
