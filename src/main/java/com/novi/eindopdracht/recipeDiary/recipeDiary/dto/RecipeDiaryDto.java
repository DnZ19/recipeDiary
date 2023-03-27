package com.novi.eindopdracht.recipeDiary.recipeDiary.dto;

import jakarta.validation.constraints.NotBlank;

public class RecipeDiaryDto {

    public Long recipeDiaryId;
    @NotBlank
    public String name;

    public String errorMessage;
}
