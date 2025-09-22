package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import za.ac.cput.domain.BusinessUser;
import za.ac.cput.domain.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BusinessUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private BusinessUser businessUser;

    @BeforeEach
    void setup() {
        businessUser = new BusinessUser.Builder()
                .setFirstName("Alice")
                .setLastName("Johnson")
                .setUsername("aliceBiz")
                .setPassword("password123")
                .setEmail("alice.biz@example.com")
                .setPhoneNumber("0829876543")
                .setUserType(UserType.BUSINESS)
                .build();
    }

    @Test
    void testRegisterBusinessUser() throws Exception {
        mockMvc.perform(post("/api/business-users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(businessUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("aliceBiz"))
                .andExpect(jsonPath("$.userType").value("BUSINESS"));
    }

   @Test
void testLoginBusinessUser() throws Exception {
    mockMvc.perform(post("/api/business-users/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(businessUser)))
            .andExpect(status().isOk());

    BusinessUserController.LoginRequest loginRequest = new BusinessUserController.LoginRequest();
    loginRequest.setUsername("aliceBiz");
    loginRequest.setPassword("password123");

    mockMvc.perform(post("/api/business-users/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(loginRequest)))
            .andExpect(status().isOk())
            .andExpect(content().string("Login successful"));
}

    @Test
    void testFindById() throws Exception {
        String response = mockMvc.perform(post("/api/business-users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(businessUser)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        BusinessUser saved = objectMapper.readValue(response, BusinessUser.class);

        mockMvc.perform(get("/api/business-users/{id}", saved.getUserId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("aliceBiz"));
    }

    @Test
    void testUpdateBusinessUser() throws Exception {
        String response = mockMvc.perform(post("/api/business-users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(businessUser)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        BusinessUser saved = objectMapper.readValue(response, BusinessUser.class);

        BusinessUser updates = new BusinessUser.Builder()
                .setEmail("alice.updated@example.com")
                .build();

        mockMvc.perform(patch("/api/business-users/{id}", saved.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("alice.updated@example.com"));
    }

    @Test
    void testDeleteBusinessUser() throws Exception {
        String response = mockMvc.perform(post("/api/business-users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(businessUser)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        BusinessUser saved = objectMapper.readValue(response, BusinessUser.class);

        mockMvc.perform(delete("/api/business-users/{id}", saved.getUserId()))
                .andExpect(status().isNoContent());
    }
}
