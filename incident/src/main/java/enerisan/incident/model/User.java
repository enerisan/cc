package enerisan.incident.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "user", schema = "city_app")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;

    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;

    @jakarta.validation.constraints.Size(max = 20)
    @jakarta.validation.constraints.NotNull
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @jakarta.validation.constraints.Size(max = 100)
    @jakarta.validation.constraints.NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @jakarta.validation.constraints.Size(max = 128)
    @Column(name = "password", length = 128)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "role_id")
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}