package com.gallery.art.server.service;

import org.springframework.http.ResponseEntity;

public interface IMailService {
    ResponseEntity<?> sendConfirmEmail(String emailTo, String code);
}
