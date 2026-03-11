package ar.com.pablocaamano.features_manager;

import ar.com.pablocaamano.features_manager.controller.FeatureController;
import ar.com.pablocaamano.features_manager.model.dto.FeatureDTO;
import ar.com.pablocaamano.features_manager.service.FeatureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WebMvcTest(controllers = FeatureController.class)
class FeatureControllerTest {
    private static final String FEATURE_NAME = "TEST_FEATURE";
    private static final String BASE_PATH = "/v1/features";
    @Autowired
    private MockMvc mvc;
    @MockBean
    private FeatureService service;
    private FeatureDTO response;

    @BeforeEach
    void setup() {
        this.response = FeatureDTO.builder()
                .feature(FEATURE_NAME).enabled(Boolean.TRUE).build();
    }

    @Test
    void testGetFeatureByNameOk() throws Exception {
        when(service.getFeatureByType(FEATURE_NAME)).thenReturn(response);
        this.mvc.perform(get(BASE_PATH.concat("/").concat(FEATURE_NAME)))
                .andExpect(status().isOk());
    }

}