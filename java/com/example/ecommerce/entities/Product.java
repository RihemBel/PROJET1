package com.example.ecommerce.entities;

import com.example.ecommerce.Config.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private Double price;
    @Column(name = "has_variant")
    @JsonProperty("hasVariant")
    private Boolean hasVariant;
    @NotNull
    @Column(name = "qttInStock", nullable = false, precision = 21, scale = 3)
    private int qttInStock;
    @Column(name = "color")
    private String color;
    @ManyToOne
    private SubCategory subCategory;
    @ManyToOne
    private Mark mark;

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

    public Double getPrice() {
        return price;
    }

    public Boolean getHasVariant() {
        return hasVariant;
    }

    public void setHasVariant(Boolean hasVariant) {
        this.hasVariant = hasVariant;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQttInStock() {
        return qttInStock;
    }

    public void setQttInStock(int qttInStock) {
        this.qttInStock = qttInStock;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", hasVariant=" + hasVariant +
                ", qttInStock=" + qttInStock +
                ", color='" + color + '\'' +
                ", subCategory=" + subCategory +
                ", mark=" + mark +
                '}';
    }
}
