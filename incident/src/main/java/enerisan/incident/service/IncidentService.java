package enerisan.incident.service;

import enerisan.incident.dto.CategoryDto;
import enerisan.incident.dto.IncidentWithCategoriesDto;
import enerisan.incident.model.Incident;
import enerisan.incident.repository.IncidentCategoryRepository;
import enerisan.incident.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncidentService {
    @Autowired
    IncidentRepository incidentRepository;
    @Autowired
    IncidentCategoryRepository incidentCategoryRepository;


    public IncidentWithCategoriesDto findIncidentWithCategoriesById(Integer id) {
        Optional<Incident> incident = incidentRepository.findById(id);
        IncidentWithCategoriesDto dto = new IncidentWithCategoriesDto();
        if (incident.isPresent()) {
            dto.setId(incident.get().getId());
            dto.setCityId(incident.get().getCity().getId());
            dto.setUserId(incident.get().getUser().getId());
            dto.setStatusId(incident.get().getStatus().getId());
            dto.setTitle(incident.get().getTitle());
            dto.setAddress(incident.get().getAddress());
            dto.setNeighborhood(incident.get().getNeighborhood());
            dto.setPostalCode(incident.get().getPostalCode());
            dto.setImageUrl(incident.get().getImage());
            dto.setDescription(incident.get().getDescription());
            dto.setCreatedAt(incident.get().getCreatedAt());
            dto.setClosedAt(incident.get().getClosedAt());
            dto.setLatitude(incident.get().getLatitude());
            dto.setLongitude(incident.get().getLongitude());

            List<CategoryDto> categories = incidentCategoryRepository.findByIncidentId(incident.get().getId())
                    .stream()
                    .map(incidentCategory -> new CategoryDto(incidentCategory.getCategory()))
                    .collect(Collectors.toList());

            List<Integer> categoryIds = categories.stream()
                    .map(CategoryDto::getId)
                    .collect(Collectors.toList());

            dto.setCategories(categories);
            dto.setCategoryIds(categoryIds);
        }

        return dto;
    }
}
