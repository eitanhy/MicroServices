package college.BankingServices.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by Eitan on 3/17/2018.
 */
@EnableEurekaServer
@SpringBootApplication

public class BankingServices {
    public static void main(String[] args) {
        // Tell Boot to look for registration-server.yml
        System.setProperty("spring.config.name", "registration-server");
        SpringApplication.run(BankingServices.class, args);
    }
}
