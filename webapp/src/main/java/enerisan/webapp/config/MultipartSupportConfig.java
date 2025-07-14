package enerisan.webapp.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class MultipartSupportConfig {

    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder(
                new SpringEncoder(() -> new HttpMessageConverters(new MappingJackson2HttpMessageConverter()))
        );
    }
}
