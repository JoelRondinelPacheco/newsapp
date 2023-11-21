package com.joel.newsapp.dtos.mail;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class SendMailDTO {
    private String to;
    private String subject;
    private String message;
}
