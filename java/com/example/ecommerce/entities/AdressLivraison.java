package com.example.ecommerce.entities;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "adressLivraison")
public class AdressLivraison implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @OneToMany(mappedBy = "adressLivraison")
    private Set<Livraison> livraison= new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Set<Livraison> getLivraison() {
        return livraison;
    }

    public void setLivraison(Set<Livraison> livraison) {
        this.livraison = livraison;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdressLivraison)) return false;
        AdressLivraison that = (AdressLivraison) o;
        return id == that.id;
    }

    @Override
    public String toString() {
        return "AdressLivraison{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", livraison=" + livraison +
                '}';
    }
}
