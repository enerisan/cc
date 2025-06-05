package enerisan.webapp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class IncidentForm {

    private Integer userId;
    private Integer statusId;
    private String title;
    private String address;
    private String neighborhood;
    private String postalCode;
    private String image;
    private String description;
    private LocalDateTime createdAt =  LocalDateTime.now();
    private LocalDateTime closedAt;
    private BigDecimal latitude;
    private BigDecimal longitude;

}
