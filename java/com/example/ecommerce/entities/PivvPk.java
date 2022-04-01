package com.example.ecommerce.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class PivvPk implements Serializable {
    private static final long serialVersionUID = -5416056929754090353L;
    @Column(name="product_id")
    private UUID ProductId;
    @Column(name="item_id")
    private UUID ItemId;
    @Column(name="variantName")
    private String VariantName;
    @Column(name="variantValueName")
    private String VariantValueName;

    public PivvPk() {
    }

    public PivvPk(UUID productId, UUID itemId, String variantName, String variantValueName) {
        ProductId = productId;
        ItemId = itemId;
        VariantName = variantName;
        VariantValueName = variantValueName;
    }

    public UUID getProductId() {
        return ProductId;
    }

    public void setProductId(UUID productId) {
        ProductId = productId;
    }

    public UUID getItemId() {
        return ItemId;
    }

    public void setItemId(UUID itemId) {
        ItemId = itemId;
    }

    public String getVariantName() {
        return VariantName;
    }

    public void setVariantName(String variantName) {
        VariantName = variantName;
    }

    public String getVariantValueName() {
        return VariantValueName;
    }

    public void setVariantValueName(String variantValueName) {
        VariantValueName = variantValueName;
    }
}
