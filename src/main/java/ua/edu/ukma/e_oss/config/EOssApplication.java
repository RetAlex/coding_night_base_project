package ua.edu.ukma.e_oss.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "ua.edu.ukma.*")
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = "ua.edu.ukma.e_request.repositories")
@EntityScan(basePackages = "ua.edu.ukma.e_request.entities")
public class  EOssApplication {

	public static void main(String[] args) {
		SpringApplication.run(EOssApplication.class, args);
	}
}
