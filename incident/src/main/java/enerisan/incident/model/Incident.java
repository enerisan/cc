package enerisan.incident.model;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "incident", schema = "city_app")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "City is mandatory")
    @Valid
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @NotNull(message = "User is mandatory")
    @Valid
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @NotNull(message = "Status is mandatory")
    @Valid
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;



    @NotBlank(message = "Title is mandatory and cannot be blank")
    @Size(max = 255)
    @Column(name = "title", nullable = false)
    @Pattern(regexp = "[\\w\\s\\-.,]+", message = "Title contains invalid characters")
    private String title;

    @NotBlank(message = "Address is mandatory and cannot be blank")
    @Lob
    @Column(name = "address", nullable = false)
    @Pattern(regexp = "[\\w\\s\\-.,\\n\\r]+", message = "Address contains invalid characters")
    private String address;

    @Size(max = 100)
    @Column(name = "neighborhood", length = 100)
    private String neighborhood;

    @Size(max = 10)
    @Pattern(regexp = "\\d{5}", message = "Le code postal doit contenir exactement 5 chiffres")
    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @Size(max = 255)
    @Column(name = "image")
    private String image;

    @Lob
    @Pattern(regexp = "[\\w\\s\\-.,\\n\\r]+", message = "Description contains invalid characters")
    @Column(name = "description")
    private String description;


    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt =  LocalDateTime.now();;


    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @Column(name = "latitude", precision = 10, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 10, scale = 6)
    private BigDecimal longitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(LocalDateTime closedAt) {
        this.closedAt = closedAt;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}