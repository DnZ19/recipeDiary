package com.novi.eindopdracht.recipeDiary.recipeDiary.controller;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.NutritionDetailsDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.PhotoDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.RecipeDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Photo;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    private final PhotoService pService;

    public PhotoController(PhotoService pService) {
        this.pService = pService;
    }

    @PostMapping
    public ResponseEntity<PhotoDto> addPhoto(@RequestParam("file") MultipartFile file,
                                             @RequestParam("dishName") String dishName) throws IOException {
        Photo photo = pService.savePhoto(file, dishName);
        PhotoDto dto = pService.convertToDto(photo);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


}
