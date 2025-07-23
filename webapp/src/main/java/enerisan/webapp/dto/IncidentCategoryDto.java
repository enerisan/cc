package enerisan.webapp.dto;

import enerisan.webapp.model.IncidentCategory;
import jakarta.validation.constraints.NotNull;


public class IncidentCategoryDto {

    @NotNull
    private Integer incidentId;

    @NotNull
    private Integer categoryId;

    public IncidentCategoryDto(Integer incidentId, Integer categoryId) {
        this.incidentId = incidentId;
        this.categoryId = categoryId;
    }

    public IncidentCategoryDto() {
    }

    public IncidentCategoryDto(IncidentCategory incidentCategory) {
        this.incidentId = incidentCategory.getId().getIncidentId();
        this.categoryId = incidentCategory.getId().getCategoryId();
    }

    public Integer getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(Integer incidentId) {
        this.incidentId = incidentId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
