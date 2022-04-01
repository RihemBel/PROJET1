package com.example.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name = "variant")
public class Variant implements Serializable {
    @Id
    @Column(name="nameVar")
    private String name;
    @OneToMany(mappedBy = "variant")
    @JsonIgnoreProperties(value = "variants", allowSetters = true)
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
        if (!(o instanceof Variant)) return false;
        Variant variant = (Variant) o;
        return Objects.equals(name, variant.name) && Objects.equals(pivvs, variant.pivvs);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Variant{" +
                "name='" + name + '\'' +
                ", pivvs=" + pivvs +
                '}';
    }
}
