package com.socaiproject.socailproject.entity;


import jakarta.persistence.*;

@Entity(name = "profile_table")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String uid;
    @Lob
    Byte[] image;

    public ProfileEntity(int id, String uid, Byte[] image) {
        this.id = id;
        this.uid = uid;
        this.image = image;
    }

    public ProfileEntity(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }
}
