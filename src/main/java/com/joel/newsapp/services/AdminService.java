package com.joel.newsapp.services;

import com.joel.newsapp.entities.Reporter;
import com.joel.newsapp.exceptions.NotFoundException;
import com.joel.newsapp.services.interfaces.IAdminService;
import com.joel.newsapp.services.interfaces.IReporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private IReporterService reporterService;
    @Override
    public void changeReporterRole(String userId, String newRole) throws NotFoundException {
        Reporter reporter = this.reporterService.findById(userId);
    }

    @Override
    public void changeUserRole(String userId, String newRole) {

    }

    @Override
    public void changeAdminRole(String userId, String newRole) {

    }
}
