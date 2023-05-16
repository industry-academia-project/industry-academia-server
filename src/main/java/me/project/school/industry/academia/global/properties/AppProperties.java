package me.project.school.industry.academia.global.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AppProperties {

    @Value("{jwt.secret.access}")
    private String accessSecret;

    @Value("{jwt.secret.refresh}")
    private String refreshSecret;

    @Value("{jwt.secret.client-Email}")
    private String clientEmail;

    @Value("{jwt.secret.clinet-secret}")
    private String clientSecret;
}
