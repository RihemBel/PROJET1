package com.example.ecommerce.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "configLivraison")
public class ConfigLivraison implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "price")
    private Double price;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "configLivraison")
    private Set<Order> order =new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof ConfigLivraison)) return false;
        ConfigLivraison that = (ConfigLivraison) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ConfigLivraison{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", order=" + order +
                '}';
    }
}
