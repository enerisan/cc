package enerisan.webapp.service.client;

import feign.Headers;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient("image-service")
public interface ImageServiceFeignClient {
    @PostMapping(value = "/api/images/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Headers("Content-Type: multipart/form-data")
    String uploadImage(@RequestPart("file") MultipartFile file);

}
