package com.socaiproject.socailproject.modal;

import lombok.Data;

@Data
public class FollowModal {

    Long id;
    int count;
    String uid;
    String userName;
    String followUser;

    public FollowModal(Long id, int count, String uid, String userName ,String followUser) {
        this.id = id;
        this.count = count;
        this.uid = uid;
        this.userName = userName;
        this.followUser=followUser;
    }

    public FollowModal() {

    }

    public String getFollowUser() {
        return followUser;
    }

    public void setFollowUser(String followUser) {
        this.followUser = followUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
