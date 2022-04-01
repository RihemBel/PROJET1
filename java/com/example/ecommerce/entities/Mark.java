package com.example.ecommerce.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "mark")
public class Mark implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    @Column(name = "name")
    private String name;
    @OneToOne
    private Image  image;
    @OneToMany(mappedBy = "mark")
    private Set<Product> product= new HashSet<>();

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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mark)) return false;
        Mark mark = (Mark) o;
        return id == mark.id;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image=" + image +
                ", product=" + product +
                '}';
    }
}
