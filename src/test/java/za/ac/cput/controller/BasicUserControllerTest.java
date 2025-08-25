package za.ac.cput.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import za.ac.cput.domain.BasicUser;
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
class BasicUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private BasicUser basicUser;

    @BeforeEach
    void setup() {
        basicUser = new BasicUser.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .setUsername("johndoe")
                .setPassword("password123")
                .setEmail("john.doe@example.com")
                .setPhoneNumber("0821234567")
                .setDateOfBirth(LocalDate.of(1990, 1, 1))
                .setIdNumber("9001011234567")
                .setLogin(true)
                .setUserType(UserType.BASIC)
                .build();
    }

    @Test
    void testRegisterBasicUser() throws Exception {
        mockMvc.perform(post("/api/basic-users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(basicUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("johndoe"))
                .andExpect(jsonPath("$.userType").value("BASIC"));
    }

    @Test
    void testLoginBasicUser() throws Exception {
        mockMvc.perform(post("/api/basic-users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(basicUser)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/basic-users/login")
                        .param("username", "johndoe")
                        .param("password", "password123"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        String response = mockMvc.perform(post("/api/basic-users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(basicUser)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        BasicUser saved = objectMapper.readValue(response, BasicUser.class);

        mockMvc.perform(get("/api/basic-users/{id}", saved.getUserId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("johndoe"));
    }

    @Test
    void testUpdateBasicUser() throws Exception {
        String response = mockMvc.perform(post("/api/basic-users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(basicUser)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        BasicUser saved = objectMapper.readValue(response, BasicUser.class);

        BasicUser updates = new BasicUser.Builder()
                .setFirstName("Jane")
                .build();

        mockMvc.perform(patch("/api/basic-users/{id}", saved.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"));
    }

    @Test
    void testDeleteBasicUser() throws Exception {
        String response = mockMvc.perform(post("/api/basic-users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(basicUser)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        BasicUser saved = objectMapper.readValue(response, BasicUser.class);

        mockMvc.perform(delete("/api/basic-users/{id}", saved.getUserId()))
                .andExpect(status().isNoContent());
    }
}
