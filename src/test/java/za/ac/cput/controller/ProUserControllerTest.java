package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import za.ac.cput.domain.ProUser;
import za.ac.cput.domain.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ProUser proUser;

    @BeforeEach
    void setup() {
        proUser = new ProUser.Builder()
                .setFirstName("Alice")
                .setLastName("Smith")
                .setUsername("alicesmith")
                .setPassword("secure123")
                .setEmail("alice.smith@example.com")
                .setPhoneNumber("0839876543")
                .setDateOfBirth(LocalDate.of(1985, 5, 15))
                .setIdNumber("8505151234567")
                .setLogin(true)
                .setUserType(UserType.PRO)
                .build();
    }

    @Test
    void testRegisterProUser() throws Exception {
        mockMvc.perform(post("/api/pro-users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(proUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("alicesmith"))
                .andExpect(jsonPath("$.userType").value("PRO"));
    }

@Test
void testLoginProUser() throws Exception {
    mockMvc.perform(post("/api/pro-users/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(proUser)))
            .andExpect(status().isOk());

    ProUserController.LoginRequest loginRequest = new ProUserController.LoginRequest();
    loginRequest.setUsername("alicesmith");
    loginRequest.setPassword("secure123");

    mockMvc.perform(post("/api/pro-users/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(loginRequest)))
            .andExpect(status().isOk())
            .andExpect(content().string("Login successful")); 
}


    @Test
    void testFindById() throws Exception {
        String response = mockMvc.perform(post("/api/pro-users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(proUser)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProUser saved = objectMapper.readValue(response, ProUser.class);

        mockMvc.perform(get("/api/pro-users/{id}", saved.getUserId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("alicesmith"));
    }

    @Test
    void testUpdateProUser() throws Exception {
        String response = mockMvc.perform(post("/api/pro-users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(proUser)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProUser saved = objectMapper.readValue(response, ProUser.class);

        ProUser updates = new ProUser.Builder()
                .setFirstName("UpdatedAlice")
                .build();

        mockMvc.perform(patch("/api/pro-users/{id}", saved.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("UpdatedAlice"));
    }

    @Test
    void testDeleteProUser() throws Exception {
        String response = mockMvc.perform(post("/api/pro-users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(proUser)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProUser saved = objectMapper.readValue(response, ProUser.class);

        mockMvc.perform(delete("/api/pro-users/{id}", saved.getUserId()))
                .andExpect(status().isNoContent());
    }
}
