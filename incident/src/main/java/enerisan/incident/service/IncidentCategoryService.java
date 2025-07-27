package enerisan.incident.service;

import enerisan.incident.dto.IncidentCategoryDto;
import enerisan.incident.model.Category;
import enerisan.incident.model.Incident;
import enerisan.incident.model.IncidentCategory;
import enerisan.incident.model.IncidentCategoryId;
import enerisan.incident.repository.CategoryRepository;
import enerisan.incident.repository.IncidentCategoryRepository;
import enerisan.incident.repository.IncidentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentCategoryService {

    private final IncidentCategoryRepository incidentCategoryRepository;
    private final IncidentRepository incidentRepository;
    private final CategoryRepository categoryRepository;

    public IncidentCategoryService(
            IncidentCategoryRepository incidentCategoryRepository,
            IncidentRepository incidentRepository,
            CategoryRepository categoryRepository) {
        this.incidentCategoryRepository = incidentCategoryRepository;
        this.incidentRepository = incidentRepository;
        this.categoryRepository = categoryRepository;
    }

    public IncidentCategory createIncidentCategory(IncidentCategoryDto dto) {
        Incident incident = incidentRepository.findById(dto.getIncidentId())
                .orElseThrow(() -> new IllegalArgumentException("Incident not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        IncidentCategoryId id = new IncidentCategoryId();
        id.setIncidentId(dto.getIncidentId());
        id.setCategoryId(dto.getCategoryId());

        IncidentCategory entity = new IncidentCategory();
        entity.setId(id);
        entity.setIncident(incident);
        entity.setCategory(category);

        return incidentCategoryRepository.save(entity);
    }

    public void delete(Integer incidentId, Integer categoryId) {
        IncidentCategoryId incidentCategoryId = new IncidentCategoryId();
        incidentCategoryId.setIncidentId(incidentId);
        incidentCategoryId.setCategoryId(categoryId);

        if (incidentCategoryRepository.existsById(incidentCategoryId)) {
            incidentCategoryRepository.deleteById(incidentCategoryId);
        } else {
            throw new EntityNotFoundException("IncidentCategory not found with incidentId "
                    + incidentId + " and categoryId " + categoryId);
        }
    }

    public IncidentCategoryDto getById(Integer incidentId, Integer categoryId) {
        IncidentCategoryId incidentCategoryId = new IncidentCategoryId();
        incidentCategoryId.setIncidentId(incidentId);
        incidentCategoryId.setCategoryId(categoryId);

        IncidentCategory incidentCategory = incidentCategoryRepository.findById(incidentCategoryId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "IncidentCategory not found with incidentId " + incidentId + " and categoryId " + categoryId));
        return new IncidentCategoryDto(incidentCategory);
    }

    public void deleteAllByIncidentId(Integer incidentId) {
        List<IncidentCategory> categories = incidentCategoryRepository.findByIncidentId(incidentId);
        incidentCategoryRepository.deleteAll(categories);
    }


}
