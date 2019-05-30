package hu.szakdolgozat.tanya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@SpringBootApplication
public class TanyaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TanyaApplication.class, args);
		System.out.println("Start");
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/token").allowedOrigins("http://localhost:4200");
				registry.addMapping("/api/**").allowedOrigins("http://localhost:4200");
			}
		};
	}

}
