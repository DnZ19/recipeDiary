package com.novi.eindopdracht.recipeDiary.recipeDiary.service;

import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.NutritionDetailsDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.dto.PhotoDto;
import com.novi.eindopdracht.recipeDiary.recipeDiary.exception.ResourceNotFoundException;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.NutritionDetails;
import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Photo;
import com.novi.eindopdracht.recipeDiary.recipeDiary.repository.PhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoService {

    private final PhotoRepository pRepos;

    public PhotoService(PhotoRepository pRepos) {
        this.pRepos = pRepos;
    }

    public Long createPhoto(PhotoDto pdto) throws IOException {
        Photo p = new Photo();
        p.setDishName(pdto.getDishName());

        if (pdto.imageData != null) {
            p.setImageData(pdto.getImageData());
        } else {
            throw new IllegalArgumentException("Image data is required");
        }

        pRepos.save(p);

        return p.getPhotoId();
    }


    public PhotoDto getPhoto(Long photoId){

        try{

            Photo p = pRepos.findById(photoId).orElseThrow(()-> new ResourceNotFoundException("Photo not available"));

            PhotoDto pdto = new PhotoDto();
            pdto.setDishName(p.getDishName());
            pdto.setPhotoId(p.getPhotoId());
            pdto.setImageData(p.getImageData());

            return pdto;


        } catch (ResourceNotFoundException exception) {
            // log the error message
            System.err.println(exception.getMessage());

            // return an error response to the browser
            PhotoDto errorResponse = new PhotoDto();
            errorResponse.errorMessage = exception.getMessage();
            return errorResponse;
        }

    }
}
