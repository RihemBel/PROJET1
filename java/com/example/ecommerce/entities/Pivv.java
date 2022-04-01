package com.example.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pivv")
public class Pivv implements Serializable {
    @EmbeddedId
    private PivvPk id;

    @JsonBackReference
    @ManyToOne
    @MapsId("ProductId")
    @JsonIgnoreProperties(value = "pivvs", allowSetters = true)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("VariantName")
    @JsonIgnoreProperties(value = "pivvs", allowSetters = true)
    @JoinColumn(name = "variantName")
    private Variant variant;

    @ManyToOne
    @MapsId("VariantValueName")
    @JsonIgnoreProperties(value = "pivvs", allowSetters = true)
    @JoinColumn(name = "variantValueName")
    private VariantValue variantValue;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @MapsId("ItemId")
    @JsonIgnoreProperties(value = "pivvs", allowSetters = true)
    @JoinColumn(name = "item_id")
    private Item item;

    public PivvPk getId() {
        return id;
    }

    public void setId(PivvPk id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public VariantValue getVariantValue() {
        return variantValue;
    }

    public void setVariantValue(VariantValue variantValue) {
        this.variantValue = variantValue;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    @Override
    public String toString() {
        return "Pivv{" +
                "id=" + id +
                ", product=" + product +
                ", variant=" + variant +
                ", variantValue=" + variantValue +
                ", item=" + item +
                '}';
    }
}
