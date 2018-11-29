package college.BankingServices.Cache;

/**
 * Created by Eitan on 3/17/2018.
 */
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.sync.RedisCommands;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CachingService {
    private static RedisClient client;
    private static String URL = "redis://localhost";
    public static RedisCommands commands;
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "Caching");
        SpringApplication.run(CachingService.class, args);
        client = RedisClient.create(URL);
        com.lambdaworks.redis.api.StatefulRedisConnection<String, String> connection = client.connect();
        commands = connection.sync();
    }
}
