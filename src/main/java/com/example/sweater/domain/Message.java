package com.example.sweater.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Username cannot be empty")
    @Size(max = 255, min = 5, message = "Message too long Or too short")
    private String title;

    private String heading;
    @NotEmpty(message = "Username cannot be empty")
    @Size(max = 2048, min = 5, message = "Message too long Or too short")
    private String description;

    private String filename;
    private String filenames;
    private String filenamesq;

    @NotEmpty(message = "Username cannot be empty")
    @Size(max = 2048, min = 5, message = "Message too long Or too short")
    private long price;
    @NotEmpty(message = "Username cannot be empty")
    @Size(max = 2048, min = 5, message = "Message too long Or too short")
    private String place;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Message() {
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public Message(String title, String heading, String description,long price,String place,User user) {
        this.title = title;
        this.heading = heading;
        this.description = description;
        this.price = price;
        this.place = place;
        this.author = user;
    }
}