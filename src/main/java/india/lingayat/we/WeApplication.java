package india.lingayat.we;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeApplication.class, args);
	}

}
