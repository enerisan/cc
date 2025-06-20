package enerisan.webapp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class IncidentForm {

    private Integer userId;
    private Integer statusId;
    private String title;
    private String address;
    private String neighborhood;
    private String category;
    private String postalCode;
    private String image;
    private String description;
    private LocalDateTime createdAt =  LocalDateTime.now();
    private LocalDateTime closedAt;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public IncidentForm() {
    }

    public IncidentForm(Integer userId, Integer statusId, String title, String address, String neighborhood, String category, String postalCode, String image, String description, LocalDateTime createdAt, LocalDateTime closedAt, BigDecimal latitude, BigDecimal longitude) {
        this.userId = userId;
        this.statusId = statusId;
        this.title = title;
        this.address = address;
        this.neighborhood = neighborhood;
        this.category = category;
        this.postalCode = postalCode;
        this.image = image;
        this.description = description;
        this.createdAt = createdAt;
        this.closedAt = closedAt;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}
