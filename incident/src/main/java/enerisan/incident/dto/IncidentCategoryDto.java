package enerisan.incident.dto;

import enerisan.incident.model.IncidentCategory;

public class IncidentCategoryDto {


    private Integer categoryId;
    private String categoryName;

    public IncidentCategoryDto(IncidentCategory incidentCategory) {
        this.categoryId = incidentCategory.getCategory().getId();
        this.categoryName = incidentCategory.getCategory().getName();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}