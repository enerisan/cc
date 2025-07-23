package enerisan.image_service.controller;


import enerisan.image_service.config.ImageServiceConfig;
import enerisan.image_service.model.IncidentImage;
import enerisan.image_service.repository.IncidentImageRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/images")
public class IncidentImageController {
    private final ImageServiceConfig config;
    private final IncidentImageRepository incidentImageRepository;

    public IncidentImageController(ImageServiceConfig imageServiceConfig, IncidentImageRepository incidentImageRepository) {
        this.incidentImageRepository = incidentImageRepository;
        this.config = imageServiceConfig;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
                                              HttpServletRequest request) throws IOException {
        IncidentImage image = new IncidentImage();
        image.setFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setData(file.getBytes());
        image.setSize(file.getSize());
        image.setUploadDate(LocalDateTime.now());

        incidentImageRepository.save(image);

        String imageUrl = config.getBaseUrl() + "/api/images/by-id/" + image.getId();


        return ResponseEntity.ok(imageUrl);
    }

    @GetMapping("/by-id/{imageId}")
    public ResponseEntity<byte[]> getImageById(@PathVariable String imageId) {
        return incidentImageRepository.findById(imageId)
                .map(image -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getFileName() + "\"")
                        .contentType(MediaType.parseMediaType(image.getContentType()))
                        .body(image.getData()))
                .orElse(ResponseEntity.notFound().build());
    }
}
