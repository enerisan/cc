package enerisan.webapp.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryIconsService {

    private static final Map<String, String> CATEGORY_ICONS;

    static {
        Map<String, String> categoryIcons = new HashMap<>();
        categoryIcons.put("Voirie", "fa-solid fa-triangle-exclamation");
        categoryIcons.put("Éclairage public", "fa-solid fa-lightbulb");
        categoryIcons.put("Trottoirs", "fa-solid fa-shoe-prints");
        categoryIcons.put("Conteneurs à déchets", "fa-solid fa-trash");
        categoryIcons.put("Aire de jeux", "fa-solid fa-child");
        categoryIcons.put("Fontaines", "fa-solid fa-faucet");
        categoryIcons.put("Propreté", "fa-solid fa-broom");
        categoryIcons.put("Gestion des déchets", "fa-solid fa-recycle");
        categoryIcons.put("Espaces verts", "fa-solid fa-tree");
        categoryIcons.put("Installations sportives", "fa-solid fa-dumbbell");
        categoryIcons.put("Bâtiments publics", "fa-solid fa-building");
        categoryIcons.put("Nuisibles", "fa-solid fa-bug");
        categoryIcons.put("Égouts", "fa-solid fa-water");
        categoryIcons.put("Circulation", "fa-solid fa-car-side");
        categoryIcons.put("Autres", "fa-solid fa-ellipsis-h");
        categoryIcons.put("Accessibilité", "fa-solid fa-wheelchair");
        CATEGORY_ICONS = Collections.unmodifiableMap(categoryIcons);
    }

    public Map<String, String> getCategoryIcons() {
        return CATEGORY_ICONS;
    }
}