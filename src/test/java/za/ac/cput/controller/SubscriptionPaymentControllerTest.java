package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import za.ac.cput.domain.*;
import za.ac.cput.factory.SubscriptionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SubscriptionPaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ProUser proUser;
    private BusinessUser businessUser;
    private SubscriptionPayment proPayment;
    private SubscriptionPayment businessPayment;

    @BeforeEach
    void setup() throws Exception {
        proUser = new ProUser.Builder()
                .setFirstName("Alice")
                .setLastName("Smith")
                .setUsername("alicepro")
                .setPassword("securepass")
                .setEmail("alice@example.com")
                .setPhoneNumber("0821112222")
                .setDateOfBirth(LocalDate.of(1988, 5, 12))
                .setIdNumber("8805123456789")
                .setLogin(true)
                .setUserType(UserType.PRO)
                .build();

        String proResponse = mockMvc.perform(post("/api/pro-users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(proUser)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        proUser = objectMapper.readValue(proResponse, ProUser.class);

        businessUser = new BusinessUser.Builder()
                .setFirstName("Bob")
                .setLastName("Johnson")
                .setUsername("bobjob")
                .setPassword("securepass")
                .setEmail("bob@example.com")
                .setPhoneNumber("0823334444")
                .setUserType(UserType.BUSINESS)
                .build();

        String busResponse = mockMvc.perform(post("/api/business-users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(businessUser)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        businessUser = objectMapper.readValue(busResponse, BusinessUser.class);

        proPayment = SubscriptionFactory.create(
                BigDecimal.valueOf(199.99),
                LocalDate.now(),
                LocalTime.now(),
                null,
                proUser
        );

        businessPayment = SubscriptionFactory.create(
                BigDecimal.valueOf(299.99),
                LocalDate.now(),
                LocalTime.now(),
                businessUser,
                null
        );
    }

    @Test
    void testCreatePaymentForProUser() throws Exception {
        mockMvc.perform(post("/api/subscription-payments/create")
                        .param("userId", proUser.getUserId().toString())
                        .param("userType", "PRO")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(proPayment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.proUser.userId").value(proUser.getUserId()))
                .andExpect(jsonPath("$.amount").value(199.99));
    }

    @Test
    void testCreatePaymentForBusinessUser() throws Exception {
        mockMvc.perform(post("/api/subscription-payments/create")
                        .param("userId", businessUser.getUserId().toString())
                        .param("userType", "BUSINESS")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(businessPayment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.businessUser.userId").value(businessUser.getUserId()))
                .andExpect(jsonPath("$.amount").value(299.99));
    }

    @Test
    void testFindById() throws Exception {
        String response = mockMvc.perform(post("/api/subscription-payments/create")
                        .param("userId", proUser.getUserId().toString())
                        .param("userType", "PRO")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(proPayment)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        SubscriptionPayment savedPayment = objectMapper.readValue(response, SubscriptionPayment.class);

        mockMvc.perform(get("/api/subscription-payments/{id}", savedPayment.getSubscriptionPaymentId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.proUser.userId").value(proUser.getUserId()));
    }

    @Test
    void testFindAllPayments() throws Exception {
        mockMvc.perform(post("/api/subscription-payments/create")
                        .param("userId", proUser.getUserId().toString())
                        .param("userType", "PRO")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(proPayment)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/subscription-payments/create")
                        .param("userId", businessUser.getUserId().toString())
                        .param("userType", "BUSINESS")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(businessPayment)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/subscription-payments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

   @Test
void testUpdatePayment() throws Exception {
    SubscriptionPayment initialPayment = SubscriptionFactory.create(
            BigDecimal.valueOf(199.99),
            LocalDate.now(),
            LocalTime.now(),
            null, 
            proUser 
    );

    String response = mockMvc.perform(post("/api/subscription-payments/create")
                    .param("userId", String.valueOf(proUser.getUserId()))
                    .param("userType", "PRO")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(initialPayment)))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    SubscriptionPayment savedPayment = objectMapper.readValue(response, SubscriptionPayment.class);

    SubscriptionPayment updates = new SubscriptionPayment.Builder()
            .setAmount(BigDecimal.valueOf(249.99))
            .setProUser(savedPayment.getProUser())       
            .setBusinessUser(savedPayment.getBusinessUser()) 
            .build();

    mockMvc.perform(patch("/api/subscription-payments/{id}", savedPayment.getSubscriptionPaymentId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updates)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.amount").value(249.99))
            .andExpect(jsonPath("$.proUser.userId").value(proUser.getUserId()));
}

    @Test
    void testDeletePayment() throws Exception {
        String response = mockMvc.perform(post("/api/subscription-payments/create")
                        .param("userId", businessUser.getUserId().toString())
                        .param("userType", "BUSINESS")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(businessPayment)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        SubscriptionPayment savedPayment = objectMapper.readValue(response, SubscriptionPayment.class);

        mockMvc.perform(delete("/api/subscription-payments/{id}", savedPayment.getSubscriptionPaymentId()))
                .andExpect(status().isNoContent());
    }
}
