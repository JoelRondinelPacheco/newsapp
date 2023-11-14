package com.joel.newsapp.services;

import com.joel.newsapp.entities.Image;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private IImageRepository imageRepository;

    @Transactional
    public Image save(MultipartFile archive) {
// TODO MANEJAR EXCEPCIONES
        if ( archive != null) {
            try {
                Image image = new Image();
                image.setMime(archive.getContentType());
                image.setName(archive.getName());
                image.setContent(archive.getBytes());
                return this.imageRepository.save(image);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    public Image update(MultipartFile archive, String id) {
        if ( archive != null) {
            try {
                Image image = new Image();

                if (id != null) {
                    Optional<Image> res =  this.imageRepository.findById(id);

                    if (res.isPresent()) {
                      image = res.get();
                    }
                }
                image.setMime(archive.getContentType());
                image.setName(archive.getName());
                image.setContent(archive.getBytes());

                return this.imageRepository.save(image);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    public Image getById(String id) throws NotFoundException {
        Optional<Image> image = this.imageRepository.findById(id);
        if (image.isPresent()) {
            return image.get();
        }
        throw new NotFoundException("Image not found");
    }

}
