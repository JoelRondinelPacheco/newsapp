package com.joel.newsapp.services;

import com.joel.newsapp.entities.Image;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private IImageRepository imageRepository;
    private static final String IMAGE_DIRECTORY = "static/img/user.jpeg";

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
    public Image saveDb(Image image) {
        return this.imageRepository.save(image);
    }
    public Image defaultImage() {
        Image img = new Image();
        ClassPathResource imagePath = new ClassPathResource(IMAGE_DIRECTORY);
        img.setName("default");
        try {
            img.setContent(Files.readAllBytes(Path.of(imagePath.getURI())));
        } catch (IOException e) {
            System.out.println("ERROR: " +e.getMessage());
        }
        img.setMime("image/jpeg");
        return this.imageRepository.save(img);
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

    public byte[] getUserPublicImage() {
        ClassPathResource imagePath = new ClassPathResource(IMAGE_DIRECTORY);
        try {
            return Files.readAllBytes(Path.of(imagePath.getURI()));
        } catch (IOException e) {
            byte[] empty = new byte[0];
            return empty;
        }
    }
}
