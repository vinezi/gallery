package com.gallery.art.server.service.impl;

import com.gallery.art.server.service.IMailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements IMailService {
    @Override
    public ResponseEntity<?> sendConfirmEmail(String emailTo, String code) {
        return null;
    }
}
