package com.example.lab12.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // blog title
//    @Column(columnDefinition = "varchar(20) not null")
    private String title;
    // body of the blog
//    @Column(columnDefinition = "varchar(255) not null")
    private String body;

    // relation .. many to one
    @ManyToOne
    @JsonIgnore
    private MyUser user;

}
