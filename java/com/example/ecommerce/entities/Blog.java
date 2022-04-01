package com.example.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "journal")
public class Blog implements Serializable{

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;


        @Size(min = 1, max = 50)
        @Column(name = "intitule")
        private String intitule;

        @ManyToMany(mappedBy = "blog")
        private Set<Image> image = new HashSet<>();

        public String getIntitule() {
            return intitule;
        }
        public Blog intitule(String intitule) {
            this.intitule = intitule;
            return this;
        }


        public void setIntitule(String intitule) {
            this.intitule = intitule;
        }

    public Set<Image> getImage() {
        return image;
    }

    public void setImage(Set<Image> image) {
        this.image = image;
    }

    public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Blog)) {
                return false;
            }
            return id != null && id.equals(((Blog) o).id);
        }

        @Override
        public int hashCode() {
            return 31;
        }

        // prettier-ignore
        @Override
        public String toString() {
            return "Journal{" +
                    "id=" + getId() +
                    "}";
        }
}
