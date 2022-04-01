package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PivvRepository extends JpaRepository<Pivv, PivvPk> {

    @Query("select distinct (p.item) from Pivv p  where p.product.id = ?1")
    List<Item> getAllItems_ofProduct(UUID productId);

    @Query("select distinct(p.product) from Pivv p  where p.item.id = ?1 ")
    Product getProduct_ofItem(UUID itemId);

    @Query("select p.variant from Pivv p  where p.product.id = ?1 ")
    List<String> getAllVariants_ofProduct(UUID ProductId);

    @Query("select p.variantValue from Pivv p  where p.item.id = ?1 ")
  List<Variant> getAllVariantValues_ofItem(UUID itemId);

    //cette requete retourne la liste des produits qui contiennent un valueVariant
    @Query("select  distinct (p.product) from Pivv p  where p.variantValue.name = ?1")
    List<Product> getAllProducts_ofVariantValue(String variantValue);

    @Query("select p from Pivv p  where p.item.id = ?1 and p.variantValue.name = ?2")
    Pivv findByItemIdAndVariantValueName(UUID id, String variantValueName);

    @Query("select distinct (p.variantValue.name) from Pivv p  where p.product.id = ?1 and p.variant.name = ?2")
    List<String> getAllVariantValues_ofVariant(UUID productId, String variantName);
}
