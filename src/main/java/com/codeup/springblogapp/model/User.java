package com.codeup.springblogapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //big int

    //username (can't be null, has to be unique)
    @Column(nullable = false, unique = true)
    private String username;

    //email
    @Column(nullable = false) //can use same email more than once but can't be no email
    private String email;

    //password - not required because of hashing, and actually safer this way
    @Column
    private String password;

    //each user can have multiple ads
    // @One[user]toMany[Ads]
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Ad> ads; //one user to many ads; will be other way around for Ad model

    //each user can have multiple posts
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;

    public User() {
    }

    public User(long id, String username, String email, String password){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(long id, String username, String email){
        this.id = id;
        this.username = username;
        this.email = email;
    }
    public User(long id, String username){
        this.id = id;
        this.username = username;
    }
    public User(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
