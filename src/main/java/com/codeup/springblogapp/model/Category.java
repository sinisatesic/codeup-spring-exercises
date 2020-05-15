package com.codeup.springblogapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    // many categories can be mapped to multiple or many ads and vice-versa
    // ergo, ManyToMany
//    @ManyToMany(mappedBy = "categories") //same as table name above; always name of what class it's in
//    private List<Ad> ads;


}
