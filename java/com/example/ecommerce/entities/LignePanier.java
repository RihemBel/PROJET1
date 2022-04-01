package com.example.ecommerce.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "lignePanier")
public class LignePanier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "nbProd")
    private int nbProd;
    @Column(name = "price")
    private Double price;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getNbProd() {
        return nbProd;
    }

    public void setNbProd(int nbProd) {
        this.nbProd = nbProd;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LignePanier)) return false;
        LignePanier that = (LignePanier) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return 31;
    }
    @Override
    public String toString() {
        return "LignePanier{" +
                "id=" + id +
                ", nbProd=" + nbProd +
                ", price=" + price +
                '}';
    }
}
