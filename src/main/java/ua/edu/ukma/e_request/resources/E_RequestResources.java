package ua.edu.ukma.e_request.resources;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "e-request")
public class E_RequestResources {
    private String smtpHost;
    private String emailUsername;
    private String emailPassword;
    private boolean sslEnabled;
    private boolean tlsEnabled;

    private String filePathPrefix = "./uploads";
}
