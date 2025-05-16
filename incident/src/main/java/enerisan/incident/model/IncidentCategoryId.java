package enerisan.incident.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IncidentCategoryId implements Serializable {
    private static final long serialVersionUID = 8004199895888174561L;
    @jakarta.validation.constraints.NotNull
    @Column(name = "incident_id", nullable = false)
    private Integer incidentId;

    @jakarta.validation.constraints.NotNull
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IncidentCategoryId entity = (IncidentCategoryId) o;
        return Objects.equals(this.incidentId, entity.incidentId) &&
                Objects.equals(this.categoryId, entity.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incidentId, categoryId);
    }

}