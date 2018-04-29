package sdu.kz.likvidator.data.network.base;

import java.io.Serializable;

public class User implements Serializable {
    public User(String name, String surname, String imageUrl, String email) {
        this.name = name;
        this.surname = surname;
        this.imageUrl = imageUrl;
        this.email = email;
    }

    public String name,surname,imageUrl,email;
    public short is_verified,is_alive;
}
