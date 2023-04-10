package com.novi.eindopdracht.recipeDiary.recipeDiary.controller;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.NutritionDetailsDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.PhotoDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Object> createPhoto(
            @Valid PhotoDto pdto,
            @RequestPart("imageData") MultipartFile imageDataFile,
            BindingResult br) throws IOException {

        if (br.hasFieldErrors()){
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()){
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage() + "\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        byte[] imageData = imageDataFile.getBytes();
        pdto.setImageData(imageData);
        Long photoId = pService.createPhoto(pdto);
        pdto.SetPhotoId(photoId);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + photoId).toUriString());

        return ResponseEntity.created(uri).body(pdto);
    }


    @GetMapping("/{photoId}")
    public ResponseEntity<PhotoDto> getPhoto(@PathVariable Long photoId){
        PhotoDto pdto = pService.getPhoto(photoId);

        return ResponseEntity.ok(pdto);
    }



}
