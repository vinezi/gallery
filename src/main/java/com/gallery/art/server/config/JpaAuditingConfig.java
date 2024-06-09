package com.gallery.art.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.OffsetDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "auditZonedDateTime")
public class JpaAuditingConfig {

    @Bean
    public DateTimeProvider auditZonedDateTime() {
        return () -> Optional.of(OffsetDateTime.now());
    }
}
