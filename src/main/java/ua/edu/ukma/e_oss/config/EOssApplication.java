package ua.edu.ukma.e_oss.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "ua.edu.ukma.*")
@EnableConfigurationProperties
public class  EOssApplication {

	public static void main(String[] args) {
		SpringApplication.run(EOssApplication.class, args);
	}
}
