package enerisan.incident.model;

import jakarta.persistence.*;

@Entity
@Table(name = "status", schema = "city_app")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "type", nullable = false, length = 50)
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}