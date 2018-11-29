package college.BankingServices.operations;

/**
 * Created by Eitan on 3/17/2018.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BankOperationsService {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "Operations");
        SpringApplication.run(BankOperationsService.class, args);
    }
}
