package com.joel.newsapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class Error404Exception {

        @ExceptionHandler(Throwable.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public String exception(Throwable throwable, ModelMap model) {
            String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
            model.addAttribute("errorMessage", errorMessage);
            return "error.html";
        }


}
