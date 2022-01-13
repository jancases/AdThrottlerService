package boot.apps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class AdvertisementApp {
	public static void main(String[] args) {
		try {
			SpringApplication.run(AdvertisementApp.class, args);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
