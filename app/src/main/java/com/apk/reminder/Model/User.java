package com.apk.reminder.Model;

public class User {
    private String id, profesi, name, email, no_hp, password,status;

    public User(String string, String id, String profesi, String name, String email, String no_hp, String password) {
        this.id = id;
        this.profesi = profesi;
        this.name = name;
        this.email = email;
        this.no_hp = no_hp;
        this.password= password;
        this.status = status;
    }

    public User(String id, String profesi, String username, String email, String no_hp, String password) {

    }

    public static void setText(String name) {

    }

    public String getId() { return id; }
    public String getProfesi() { return profesi; }

    public String getName() {return name;}

    public String getEmail() {return email; }

    public String getNo_hp() {return no_hp;}
    public String getPassword() { return password; }
    public String getStatus(){return status;}



    public void setId(String id) {
        this.id = id;
    }

    public void setProfesi(String profesi) {
        this.profesi = profesi;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
