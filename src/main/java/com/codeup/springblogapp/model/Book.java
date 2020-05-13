package com.codeup.springblogapp.model;

import javax.persistence.*;

@Entity
public class Fortuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String description;
}
