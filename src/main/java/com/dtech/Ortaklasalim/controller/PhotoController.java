package com.dtech.Ortaklasalim.controller;

import com.dtech.Ortaklasalim.model.Photo;
import com.dtech.Ortaklasalim.service.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(path="/photos")
public class PhotoController {

    @Autowired
    PhotoRepository photoRepository;

    @GetMapping("")
    public @ResponseBody Iterable<Photo> getPhotos() {
        return photoRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Optional<Photo> getSpecificPhoto(@PathVariable Integer id) {
        return photoRepository.findById(id);
    }

    @PostMapping("/add")
    public @ResponseBody HttpStatus addPhoto(@RequestPart("data") MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setFilename(file.getName());
        // HARDCODED
        photo.setPost_id(1);
        // HARDCODED
        photo.setOwner_id(1);
        photo.setData(file.getBytes());
        photo.setContentType(file.getContentType());
        try {
            photoRepository.save(photo);
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.OK;
    }
}
