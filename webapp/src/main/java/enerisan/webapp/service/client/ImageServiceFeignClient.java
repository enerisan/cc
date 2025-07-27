package enerisan.webapp.service.client;

import feign.Headers;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@FeignClient("image-service")
public interface ImageServiceFeignClient {
    @PostMapping(value = "/api/images/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Headers("Content-Type: multipart/form-data")
    String uploadImage(@RequestPart("file") MultipartFile file);

    @PutMapping("/api/images/by-id/{imageId}/assign-incident/{incidentId}")
    void assignIncidentIdToImage(@PathVariable("imageId") String imageId, @PathVariable("incidentId") String incidentId);

    @DeleteMapping("/api/images/by-incident/{incidentId}")
    void deleteImageByIncidentId(@PathVariable("incidentId") String incidentId);
}
