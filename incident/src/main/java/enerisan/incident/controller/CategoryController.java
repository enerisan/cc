package enerisan.incident.controller;

import enerisan.incident.dto.CategoryDto;
import enerisan.incident.model.Category;
import enerisan.incident.model.Incident;
import enerisan.incident.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<CategoryDto> getAllCategory() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryDto(category))
                .collect(Collectors.toList());
    }
}
