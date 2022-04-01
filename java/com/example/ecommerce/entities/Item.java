package com.example.ecommerce.entities;

import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="item_id")
    private UUID id;
    @NotNull
    @Column(name = "name")
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @NotNull
    @Column(name = "qttInStock", nullable = false, precision = 21, scale = 3)
    private int qttInStock;
    @Column(name="price")
    private Double price;
    @OneToMany(mappedBy = "item")
    private Set<Image> image = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "item_panier",
            joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "panier_id", referencedColumnName = "panier_id"))
    private Set<Panier> panier=new HashSet<>();
    @ManyToMany
    @JoinTable(name = "item_order",
    joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "item_id"),
    inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"))
    private Set<Order> orders=new HashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQttInStock() {
        return qttInStock;
    }

    public void setQttInStock(int qttInStock) {
        this.qttInStock = qttInStock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Image> getImage() {
        return image;
    }

    public void setImage(Set<Image> image) {
        this.image = image;
    }

    public Set<Panier> getPanier() {
        return panier;
    }

    public void setPanier(Set<Panier> panier) {
        this.panier = panier;
    }

    public Set<Order> getOrder() {
        return orders;
    }

    public void setOrder(Set<Order> order) {
        this.orders = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", qttInStock=" + qttInStock +
                ", price=" + price +
                ", image=" + image +
                ", panier=" + panier +
                ", orders=" + orders +
                '}';
    }
}
