package college.BankingServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BankInfoService {

	public static void main(String[] args) {
        System.setProperty("spring.config.name", "InfoService");
		SpringApplication.run(BankInfoService.class, args);
	}



}
