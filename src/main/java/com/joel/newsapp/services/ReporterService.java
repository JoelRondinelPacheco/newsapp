package com.joel.newsapp.services;

import com.joel.newsapp.dtos.reporter.ReporterEditReqDTO;
import com.joel.newsapp.dtos.reporter.ReporterPostReqDTO;
import com.joel.newsapp.entities.Reporter;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.repositories.IReporterRepository;
import com.joel.newsapp.services.interfaces.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReporterService implements ICrudService<Reporter, ReporterPostReqDTO, ReporterEditReqDTO, String> {
    @Autowired
    private IReporterRepository reporterRepository;

    @Override
    public Reporter save(ReporterPostReqDTO reporterPostReqDTO) throws Exception {
        return null;
    }

    @Override
    public Reporter getById(String s) throws NotFoundException {
        return null;
    }

    @Override
    public Reporter edit(ReporterEditReqDTO reporterEditReqDTO) throws NotFoundException {
        return null;
    }

    @Override
    public String deleteById(String s) {
        return null;
    }

    public Reporter findByEmail(String email) throws NotFoundException {
        Optional<Reporter> reporterOptional = this.reporterRepository.findByEmail(email);
        if (reporterOptional.isPresent()) {
            return reporterOptional.get();
        }
        throw new NotFoundException("Reporter not found");
    }
}
