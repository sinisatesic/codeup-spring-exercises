package com.codeup.springblogapp.model;


import javax.persistence.*;

@Entity
@Table(name = "ad_images")
public class AdImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // will be common input for tables through Spring

    //each ad_image will have an "id", "path", and "ad" that it's attached to

    // path for the image
    @Column(nullable = false)
    private String path;

    // the 'ad' the image is attached to
    //many images can be attached to one ad listing
    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;

    public AdImage() {
    }

    public AdImage(String path, Ad ad){
        this.path = path;
        this.ad = ad;
    }

    public AdImage(long id, String path, Ad ad){
        this.id = id;
        this.path = path;
        this.ad = ad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
}
