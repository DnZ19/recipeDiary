package com.novi.eindopdracht.recipeDiary.recipeDiary.repository;

import com.novi.eindopdracht.recipeDiary.recipeDiary.model.Photo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository extends CrudRepository <Photo, Long> {

}
