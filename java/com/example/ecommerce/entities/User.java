package com.example.ecommerce.entities;

import com.example.ecommerce.Config.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID id;
    @javax.validation.constraints.NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;
    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstname;
    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastname;
    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true, name = "email")
    private String email;
    @JsonIgnore
    @NotNull
    @Size(min = 10, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    @BatchSize(size = 20)
    @Column(name = "Roles")
    private Set<Role> roles=new HashSet<>();
    @Column(name = "sexe")
    private String sexe;
    @Column(name = "adresse")
    private  String adresse;
    @Column(name = "dateNaissance")
    private Date dateNaissance;
    @Size(max = 15)
    @Column(name = "phone")
    private String phone;

    @OneToOne
    private Image image;
    @OneToOne
    private Panier panier;
    @OneToMany(mappedBy = "user")
    private Set<Order> order = new HashSet<>();

    public User() {
    }

    public User(UUID id, String firstname, String lastname) {
        this.id=id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User(String subject, String firstname, Collection<? extends GrantedAuthority> authorities) {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
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
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", sexe='" + sexe + '\'' +
                ", adresse='" + adresse + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", phone='" + phone + '\'' +
         //       ", dateCrea=" + dateCrea +
                ", image=" + image +
                ", panier=" + panier +
                ", order=" + order +
                '}';
    }
}
