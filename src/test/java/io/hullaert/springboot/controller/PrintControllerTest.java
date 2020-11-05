package io.hullaert.springboot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PrintControllerTest {

    private final String ids = "[\"1\",\"2\"]";

    @Autowired
    private MockMvc mvc;

    @Test
    public void health() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/tickets/print").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(ids))
                .andExpect(status().isOk());
    }

    /**
     * @throws Exception if perform method fail
     */
    @Test
    public void returnThePrintedTicket() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/tickets/print")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(ids)
        ).andExpect(status().isOk())
        .andExpect(content().string(notNullValue()));
    }
}