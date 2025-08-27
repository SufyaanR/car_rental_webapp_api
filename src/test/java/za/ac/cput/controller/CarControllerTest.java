package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.BusinessUser;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.UserType;
import za.ac.cput.service.BusinessUserServiceImpl;

import java.math.BigDecimal;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BusinessUserServiceImpl businessUserService;

    private BusinessUser owner;
    private Car car;

    @BeforeEach
    void setup() {
        owner = new BusinessUser.Builder()
                .setFirstName("Alice")
                .setLastName("Johnson")
                .setUsername("aliceBiz")
                .setPassword("password123")
                .setEmail("alice.biz@example.com")
                .setPhoneNumber("0829876543")
                .setUserType(UserType.BUSINESS)
                .build();

        owner = businessUserService.register(owner);

        car = new Car.Builder()
                .setBrand("Toyota")
                .setModel("Corolla")
                .setType("Sedan")
                .setPricePerDay(BigDecimal.valueOf(500))
                .setSeatCapacity(5)
                .setBootCapacity(450f)
                .setEngineCapacity(1.8f)
                .setTransmission("Automatic")
                .setDescription("Reliable sedan")
                .setCollectionLocation("Cape Town")
                .setIsAvailable(true)
                .setBusinessUser(owner)
                .build();
    }

    @Test
    void testSaveCar() throws Exception {
        MockMultipartFile imageFile = new MockMultipartFile(
                "image", "car.jpg", "image/jpeg", new byte[]{1, 2, 3, 4});

        mockMvc.perform(multipart("/api/cars")
                        .file(imageFile)
                        .param("brand", car.getBrand())
                        .param("model", car.getModel())
                        .param("type", car.getType())
                        .param("pricePerDay", car.getPricePerDay().toString())
                        .param("seatCapacity", String.valueOf(car.getSeatCapacity()))
                        .param("bootCapacity", String.valueOf(car.getBootCapacity()))
                        .param("engineCapacity", String.valueOf(car.getEngineCapacity()))
                        .param("transmission", car.getTransmission())
                        .param("description", car.getDescription())
                        .param("collectionLocation", car.getCollectionLocation())
                        .param("isAvailable", String.valueOf(car.getIsAvailable()))
                        .param("businessUserId", String.valueOf(owner.getUserId()))
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand").value("Toyota"))
                .andExpect(jsonPath("$.model").value("Corolla"));
    }

    @Test
    void testFindById() throws Exception {
        String response = mockMvc.perform(multipart("/api/cars")
                        .param("brand", car.getBrand())
                        .param("model", car.getModel())
                        .param("type", car.getType())
                        .param("pricePerDay", car.getPricePerDay().toString())
                        .param("seatCapacity", String.valueOf(car.getSeatCapacity()))
                        .param("bootCapacity", String.valueOf(car.getBootCapacity()))
                        .param("engineCapacity", String.valueOf(car.getEngineCapacity()))
                        .param("transmission", car.getTransmission())
                        .param("description", car.getDescription())
                        .param("collectionLocation", car.getCollectionLocation())
                        .param("isAvailable", String.valueOf(car.getIsAvailable()))
                        .param("businessUserId", String.valueOf(owner.getUserId()))
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();

        Car savedCar = objectMapper.readValue(response, Car.class);

        mockMvc.perform(get("/api/cars/{id}", savedCar.getCarId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand").value("Toyota"));
    }

    @Test
    void testUpdateCar() throws Exception {
        String response = mockMvc.perform(multipart("/api/cars")
                        .param("brand", car.getBrand())
                        .param("model", car.getModel())
                        .param("type", car.getType())
                        .param("pricePerDay", car.getPricePerDay().toString())
                        .param("seatCapacity", String.valueOf(car.getSeatCapacity()))
                        .param("bootCapacity", String.valueOf(car.getBootCapacity()))
                        .param("engineCapacity", String.valueOf(car.getEngineCapacity()))
                        .param("transmission", car.getTransmission())
                        .param("description", car.getDescription())
                        .param("collectionLocation", car.getCollectionLocation())
                        .param("isAvailable", String.valueOf(car.getIsAvailable()))
                        .param("businessUserId", String.valueOf(owner.getUserId()))
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();

        Car savedCar = objectMapper.readValue(response, Car.class);

        Car updates = new Car.Builder()
                .setDescription("Updated description")
                .setBusinessUser(savedCar.getBusinessUser())
                .setProUser(savedCar.getProUser())
                .build();

        mockMvc.perform(patch("/api/cars/{id}", savedCar.getCarId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Updated description"));
    }

    @Test
    void testDeleteCar() throws Exception {
        String response = mockMvc.perform(multipart("/api/cars")
                        .param("brand", car.getBrand())
                        .param("model", car.getModel())
                        .param("type", car.getType())
                        .param("pricePerDay", car.getPricePerDay().toString())
                        .param("seatCapacity", String.valueOf(car.getSeatCapacity()))
                        .param("bootCapacity", String.valueOf(car.getBootCapacity()))
                        .param("engineCapacity", String.valueOf(car.getEngineCapacity()))
                        .param("transmission", car.getTransmission())
                        .param("description", car.getDescription())
                        .param("collectionLocation", car.getCollectionLocation())
                        .param("isAvailable", String.valueOf(car.getIsAvailable()))
                        .param("businessUserId", String.valueOf(owner.getUserId()))
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();

        Car savedCar = objectMapper.readValue(response, Car.class);

        mockMvc.perform(delete("/api/cars/{id}", savedCar.getCarId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdateAvailability() throws Exception {
        String response = mockMvc.perform(multipart("/api/cars")
                        .param("brand", car.getBrand())
                        .param("model", car.getModel())
                        .param("type", car.getType())
                        .param("pricePerDay", car.getPricePerDay().toString())
                        .param("seatCapacity", String.valueOf(car.getSeatCapacity()))
                        .param("bootCapacity", String.valueOf(car.getBootCapacity()))
                        .param("engineCapacity", String.valueOf(car.getEngineCapacity()))
                        .param("transmission", car.getTransmission())
                        .param("description", car.getDescription())
                        .param("collectionLocation", car.getCollectionLocation())
                        .param("isAvailable", String.valueOf(car.getIsAvailable()))
                        .param("businessUserId", String.valueOf(owner.getUserId()))
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();

        Car savedCar = objectMapper.readValue(response, Car.class);

        mockMvc.perform(post("/api/cars/{id}/availability", savedCar.getCarId())
                        .param("isAvailable", "false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isAvailable").value(false));
    }
}
