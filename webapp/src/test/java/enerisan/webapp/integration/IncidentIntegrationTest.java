package enerisan.webapp.integration;

import enerisan.webapp.controller.IncidentController;
import enerisan.webapp.dto.IncidentForm;
import enerisan.webapp.model.Incident;
import enerisan.webapp.model.User;

import enerisan.webapp.service.IncidentService;
import enerisan.webapp.service.SessionService;
import enerisan.webapp.service.UserService;
import enerisan.webapp.service.client.ImageServiceFeignClient;
import enerisan.webapp.service.client.IncidentFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class IncidentIntegrationSpringBootTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private SessionService sessionService;

    @MockBean
    private ImageServiceFeignClient imageServiceFeignClient;

    @MockBean
    private IncidentFeignClient incidentFeignClient;

    @Test
    @WithMockUser(username = "testuser")
    void shouldCreateIncidentSuccessfully() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        // Mock session user
        User fakeUser = new User();
        fakeUser.setId(1);
        when(sessionService.sessionUser()).thenReturn(fakeUser);

        // Mock image upload
        when(imageServiceFeignClient.uploadImage(any())).thenReturn("http://fake-url.com/image.png");
        doNothing().when(imageServiceFeignClient).assignIncidentIdToImage(any(), any());

        // Mock Feign client for incident creation
        when(incidentFeignClient.createIncident(any())).thenAnswer(invocation -> {
            Incident incident = invocation.getArgument(0, Incident.class);
            incident.setId(1); // Simulates the backend assigning an ID to the newly created incident
            return incident;
        });
        doNothing().when(incidentFeignClient).addIncidentCategory(any());

        MockMultipartFile imageFile = new MockMultipartFile(
                "image",
                "test-image.png",
                "image/png",
                "fake-image-content".getBytes()
        );

        mockMvc.perform(multipart("/addIncident")
                        .file(imageFile)
                        .param("title", "")
                        .param("address", "9 Rue Mignet")
                        .param("cityId", "1")
                        .param("description", "Test incident")
                        .param("postalCode", "13100")
                        .param("categoryIds", "1", "2")
                        .with(csrf())
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().is3xxRedirection()); // It should redirect if incident creation has succeeded
    }
}
