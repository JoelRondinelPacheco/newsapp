package com.joel.newsapp.services.interfaces;

import com.joel.newsapp.dtos.reporter.EditReporterDTO;
import com.joel.newsapp.dtos.reporter.RegisterReporterDTO;
import com.joel.newsapp.dtos.reporter.ReporterInfoDTO;
import com.joel.newsapp.entities.Reporter;
import com.joel.newsapp.exceptions.NotFoundException;

import java.util.List;

public interface IReporterService extends ICrudService<ReporterInfoDTO, RegisterReporterDTO, EditReporterDTO, String>{
    List<ReporterInfoDTO> getAllReporters();
    Reporter findByEmail(String email) throws NotFoundException;
    Reporter findById(String id) throws NotFoundException;
}
