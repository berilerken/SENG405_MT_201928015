package com.example.seng405_mt_201928015;

//bu class apiden çektiğimiz dataların özelliklerini taşıyan class
public class DataClass {

    private String firstName;
    private String lastName;
    private String email;
    private String image;


    //Verilerin (JSON) işlemleri için kullanılan bir veri sınıfıdır.
    // "email", "first_name" ve "last_name" attributelarını bulundurur.
    public DataClass(String firstName, String lastName, String email, String image_url) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.image = image_url;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }
}
