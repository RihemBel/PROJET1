package com.example.ecommerce.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "panier")
public class Panier implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name="panier_id")
    private UUID id;
    @Column(name = "date_creation")
    private Date dateCrea;
    @OneToOne
    private User user;
    @OneToOne
    private Order order;
    @ManyToMany(mappedBy = "panier")
    private Set<Item> item=new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDateCrea() {
        return dateCrea;
    }

    public void setDateCrea(Date dateCrea) {
        this.dateCrea = dateCrea;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Set<Item> getItem() {
        return item;
    }

    public void setItem(Set<Item> item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Panier)) return false;
        Panier panier = (Panier) o;
        return id == panier.id;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id=" + id +
                ", dateCrea=" + dateCrea +
                ", user=" + user +
                ", order=" + order +
                ", item=" + item +
                '}';
    }
}
