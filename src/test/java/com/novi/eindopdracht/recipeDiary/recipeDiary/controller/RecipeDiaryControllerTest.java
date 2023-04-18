package com.novi.eindopdracht.recipeDiary.recipeDiary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDiaryDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.RecipeDiary;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.RecipeDiaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class RecipeDiaryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RecipeDiaryService recipeDiaryService;

    public RecipeDiaryDto toRecipeDiaryDto(RecipeDiary recipeDiary) {
        RecipeDiaryDto recipeDiaryDto = new RecipeDiaryDto();
        recipeDiaryDto.setName(recipeDiary.getName());
        return recipeDiaryDto;
    }

    @BeforeEach
    void setUp() {
        RecipeDiary recipeDiary = new RecipeDiary();
        recipeDiary.setName("Test Diary");
        RecipeDiaryDto recipeDiaryDto = toRecipeDiaryDto(recipeDiary);
        recipeDiaryService.createRecipeDiary(recipeDiaryDto);
    }

    @Test
    void getAllRecipesForDiary() throws Exception {
        Long recipeDiaryId = 1L;

        mockMvc.perform(get("/recipeDiary/{recipeDiaryId}/recipes", recipeDiaryId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }
}
