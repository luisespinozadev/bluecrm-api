package com.luisespinozav.bluecrm.api.healthcheck;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HealthController.class)
public class HealthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void givenSystemIsUp_whenQueryHealth_thenStatus200() throws Exception {

        ResultActions response = mockMvc.perform(get("/health"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ok"));

    }
}
