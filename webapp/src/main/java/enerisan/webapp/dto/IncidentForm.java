package enerisan.webapp.dto;

import enerisan.webapp.model.City;
import enerisan.webapp.model.Incident;
import enerisan.webapp.model.Status;
import enerisan.webapp.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class IncidentForm {
    private Integer id;
    private Integer cityId;
    private Integer userId;
    private Integer statusId;

    private String title;
    private String address;
    private String neighborhood;
    private String postalCode;
    private MultipartFile image;
    private String imageUrl;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime closedAt;

    private BigDecimal latitude;
    private BigDecimal longitude;

    private List<Integer> categoryIds;
    private List<CategoryDto> categories;

    public IncidentForm() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public Incident toIncident() {
        Incident incident = new Incident();

        City city = new City();
        city.setId(this.cityId);
        incident.setCity(city);

        User user = new User();
        user.setId(this.userId);
        incident.setUser(user);

        Status status = new Status();
        status.setId(this.statusId != null ? this.statusId : 1);
        incident.setStatus(status);

        incident.setTitle(this.title);
        incident.setAddress(this.address);
        incident.setNeighborhood(this.neighborhood != null ? this.neighborhood : "");
        incident.setPostalCode(this.postalCode);
        incident.setImage(this.imageUrl != null ? this.imageUrl : "imagen.jpg");
        incident.setDescription(this.description);
        incident.setCreatedAt(this.createdAt != null ? this.createdAt : LocalDateTime.now());
        incident.setClosedAt(this.closedAt);
        incident.setLatitude(this.latitude != null ? this.latitude : new BigDecimal("0.0"));
        incident.setLongitude(this.longitude != null ? this.longitude : new BigDecimal("0.0"));

        return incident;
    }
}
