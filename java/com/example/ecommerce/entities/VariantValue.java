package com.example.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "variantValue")
public class VariantValue implements Serializable {
    @Id
    @Column(name = "nameVarV")
    private String name;
    @OneToMany(mappedBy = "variantValue")
    @JsonIgnoreProperties(value = "variantValues", allowSetters = true)
    private Set<Pivv> pivvs = new HashSet<Pivv>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Pivv> getPivvs() {
        return pivvs;
    }

    public void setPivvs(Set<Pivv> pivvs) {
        this.pivvs = pivvs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VariantValue)) return false;
        VariantValue that = (VariantValue) o;
        return Objects.equals(name, that.name) && Objects.equals(pivvs, that.pivvs);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "VariantValue{" +
                "name='" + name + '\'' +
                ", pivvs=" + pivvs +
                '}';
    }
}

