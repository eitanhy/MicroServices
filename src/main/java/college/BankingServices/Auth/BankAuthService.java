package college.BankingServices.Auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class BankAuthService {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "AuthService");
        SpringApplication.run(BankAuthService.class, args);
    }



}