package com.dmholland.demo.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Lob //Recognized as a Blob(binary body), this helps get large pieces of data
    @Column(nullable = false)
    private String body;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)//This means that without an author, an error will cause it not to persist, also lazy means it will only fetch when chosen
    private User author;

    @NotNull
    @Column(nullable = false)
    private Date date = new Date();

    public Post() {
    }


    public Post(Long id, String title, String body, User author) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Posts{" + "id= " + id + ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '}';
    }


}
