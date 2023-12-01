package com.joel.newsapp.services;

import com.joel.newsapp.entities.Moderator;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IModeratorRepository;
import com.joel.newsapp.services.interfaces.IModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModeratorService implements IModeratorService {
    @Autowired private IModeratorRepository moderatorRepository;

    @Override
    public Moderator findByUserId(String userId) throws NotFoundException {
        Optional<Moderator> mod = this.moderatorRepository.findByUser_Id(userId);
        if (mod.isPresent()) {
            return mod.get();
        }
        throw new NotFoundException("Moderator not found");
    }
}
