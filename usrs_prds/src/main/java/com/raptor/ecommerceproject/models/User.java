package com.raptor.ecommerceproject.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = ("users"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String mail;
    private String address;
    private String phone;
    private String typeUser;
    private String password;


    @OneToMany(mappedBy = "user")
    private List<Product> products;

    @OneToMany(mappedBy = "userOrder")
    private List<Order> orders;


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }





    public User(Long id, String name, String username, String mail, String address, String phone, String typeUser, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.mail = mail;
        this.address = address;
        this.phone = phone;
        this.typeUser = typeUser;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", typeUser='" + typeUser + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
