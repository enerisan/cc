package enerisan.webapp.model;



public class IncidentCategory {


    private IncidentCategoryId id;


    private Incident incident;


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