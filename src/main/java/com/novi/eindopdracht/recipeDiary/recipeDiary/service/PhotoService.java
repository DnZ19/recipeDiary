package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.PhotoDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Photo;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Recipe;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.PhotoRepository;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PhotoService {

    private final PhotoRepository pRepos;
    private final RecipeRepository rRepos;
    private static final Logger logger = LoggerFactory.getLogger(PhotoService.class);


    public PhotoService(PhotoRepository pRepos, RecipeRepository rRepos) {
        this.pRepos = pRepos;
        this.rRepos = rRepos;
    }

    public Photo savePhoto(Long recipeId, MultipartFile file, String dishName) throws IOException {
        Recipe recipe = rRepos.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Photo not found"));
        Photo photo = new Photo();
        photo.setRecipe(recipe);
        photo.setDishName(dishName);
        photo.setImageData(file.getBytes());
        return pRepos.save(photo);
    }

    public PhotoDto convertToDto(Photo photo){

        PhotoDto pdto = new PhotoDto();
        pdto.setRecipe(photo.getRecipe());
        pdto.setPhotoId(photo.getPhotoId());
        pdto.setDishName(photo.getDishName());
        pdto.setImageData(Base64.getEncoder().encodeToString(photo.getImageData()));
        return pdto;

    }

}
