package UsedStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@Configuration
@ImportResource("classpath:databaseContext.xml")
public class StoreBackApplication {
	public static void main(String[] args) {
		SpringApplication.run(StoreBackApplication.class, args);
	}
}
