package com.example.ecommerce.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "livraison")
public class Livraison implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name="name")
    private String name;
    @Column(name="date")
    private Date date;
    @Column(name="etat")
    private String etat;
    @Column(name="type")
    private String type;
    @ManyToOne
    private AdressLivraison adressLivraison;
    @OneToMany(mappedBy = "livraison")
    private Set<Order> order= new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AdressLivraison getAdressLivraison() {
        return adressLivraison;
    }

    public void setAdressLivraison(AdressLivraison adressLivraison) {
        this.adressLivraison = adressLivraison;
    }

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Livraison)) return false;
        Livraison livraison = (Livraison) o;
        return id == livraison.id;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Livraison{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", etat='" + etat + '\'' +
                ", type='" + type + '\'' +
                ", adressLivraison=" + adressLivraison +
                ", order=" + order +
                '}';
    }
}
