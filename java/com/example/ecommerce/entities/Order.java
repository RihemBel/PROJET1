package com.example.ecommerce.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private UUID id;
    @Column(name="etat")
    private String etat;
    @Column(name = "date")
    private Date date;
    @ManyToOne
    private User user;
    @ManyToOne
    private ConfigLivraison configLivraison;
    @ManyToOne
    private Livraison livraison;
    @OneToOne
    private Panier panier;
    @ManyToMany(mappedBy = "orders")
    private Set<Item> items=new HashSet<>();

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ConfigLivraison getConfigLivraison() {
        return configLivraison;
    }

    public void setConfigLivraison(ConfigLivraison configLivraison) {
        this.configLivraison = configLivraison;
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", etat='" + etat + '\'' +
                ", date=" + date +
                ", user=" + user +
                ", configLivraison=" + configLivraison +
                ", livraison=" + livraison +
                ", panier=" + panier +
                ", items=" + items +
                '}';
    }
}
