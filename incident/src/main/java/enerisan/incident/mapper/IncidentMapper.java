package enerisan.incident.mapper;

import enerisan.incident.dto.CategoryDto;
import enerisan.incident.dto.IncidentCategoryDto;
import enerisan.incident.dto.IncidentWithCategoriesDto;
import enerisan.incident.model.Incident;
import enerisan.incident.model.IncidentCategory;
import enerisan.incident.repository.IncidentCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IncidentMapper {


    @Autowired
    private IncidentCategoryRepository incidentCategoryRepository;
    public IncidentWithCategoriesDto incidentToIncidentWithCategoriesDto(Incident incident) {
        IncidentWithCategoriesDto incidentWithCategoriesDto = new IncidentWithCategoriesDto();
        incidentWithCategoriesDto.setId(incident.getId());
        incidentWithCategoriesDto.setCityId(incident.getCity().getId());
        incidentWithCategoriesDto.setCityName(incident.getCity().getName());

        incidentWithCategoriesDto.setUserId(incident.getUser().getId());
        incidentWithCategoriesDto.setStatusId(incident.getStatus().getId());
        incidentWithCategoriesDto.setTitle(incident.getTitle());
        incidentWithCategoriesDto.setAddress(incident.getAddress());
        incidentWithCategoriesDto.setNeighborhood(incident.getNeighborhood());
        incidentWithCategoriesDto.setPostalCode(incident.getPostalCode());
        incidentWithCategoriesDto.setImageUrl(incident.getImage());
        incidentWithCategoriesDto.setDescription(incident.getDescription());
        incidentWithCategoriesDto.setCreatedAt(incident.getCreatedAt());
        incidentWithCategoriesDto.setClosedAt(incident.getClosedAt());
        incidentWithCategoriesDto.setLatitude(incident.getLatitude());
        incidentWithCategoriesDto.setLongitude(incident.getLongitude());

        List<CategoryDto> categories = incidentCategoryRepository.findByIncidentId(incident.getId())
                .stream()
                .map(incidentCategory -> new CategoryDto(incidentCategory.getCategory()))
                .collect(Collectors.toList());

        List<Integer> categoryIds = categories.stream()
                .map(CategoryDto::getId)
                .collect(Collectors.toList());

        incidentWithCategoriesDto.setCategories(categories);
        incidentWithCategoriesDto.setCategoryIds(categoryIds);
        return incidentWithCategoriesDto;
    }

}
