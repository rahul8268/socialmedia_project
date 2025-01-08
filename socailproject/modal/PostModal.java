package com.socaiproject.socailproject.modal;




public class PostModal {
    Long id;
    String uid;
    Byte[] image;
    String caption;
    String userName;

    public PostModal(){

    }


    public PostModal(Long id, String uid, Byte[] image, String caption, String user_name) {
        this.id = id;
        this.uid = uid;
        this.image = image;
        this.caption = caption;
        this.userName = user_name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
