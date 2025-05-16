package enerisan.incident.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "incident_category", schema = "city_app")
public class IncidentCategory {
    @EmbeddedId
    private IncidentCategoryId id;

    @MapsId("incidentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "incident_id", nullable = false)
    private Incident incident;

    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public IncidentCategoryId getId() {
        return id;
    }

    public void setId(IncidentCategoryId id) {
        this.id = id;
    }

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}