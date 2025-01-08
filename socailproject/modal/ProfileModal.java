package com.socaiproject.socailproject.modal;

public class ProfileModal {
    long id;
    String uid;
    Byte[] image;

    public ProfileModal(int id, String uid, Byte[] image) {
        this.id = id;
        this.uid = uid;
        this.image = image;
    }

    public ProfileModal(){

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
