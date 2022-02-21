package br.com.lsdi.uhealthcare.model;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    public long id;
    public String name;
    public String avatar;


    public User(long id, String name, String avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }

}
