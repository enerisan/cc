package enerisan.webapp.mapper;

import enerisan.webapp.dto.IncidentForm;
import enerisan.webapp.dto.IncidentWithCategoriesDto;

public class IncidentMapper {

    public static IncidentForm toForm(IncidentWithCategoriesDto dto) {
        if (dto == null) {
            return null;
        }

        IncidentForm form = new IncidentForm();

        form.setId(dto.getId());
        form.setTitle(dto.getTitle());
        form.setDescription(dto.getDescription());
        form.setAddress(dto.getAddress());
        form.setNeighborhood(dto.getNeighborhood());
        form.setPostalCode(dto.getPostalCode());
        form.setImageUrl(dto.getImageUrl());
        form.setLatitude(dto.getLatitude());
        form.setLongitude(dto.getLongitude());
        form.setCreatedAt(dto.getCreatedAt());
        form.setClosedAt(dto.getClosedAt());

        form.setCityId(dto.getCityId());
        form.setUserId(dto.getUserId());
        form.setStatusId(dto.getStatusId());
        form.setCategoryIds(dto.getCategoryIds());

        return form;
    }
}
