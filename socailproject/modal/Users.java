package com.socaiproject.socailproject.modal;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Users {
  private   Long id;
    private String uid;
  private   String userName;
    private String password;
    private String email;

     Users(Long id, String uid, String userName, String password, String email) {
        this.id = id;
        this.uid = uid;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    public Users(){

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
