package enerisan.image_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageServiceConfig {


    @Value("${IMAGE_SERVICE_BASE_URL}")
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }


}
