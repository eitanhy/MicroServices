package college.BankingServices.operations;

/**
 * Created by Eitan on 4/14/2018.
 */
import college.BankingServices.HttpFuncs;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class BankingOperationsServiceController {

    private boolean validateToken(String token){
        String result = HttpFuncs.getResponse("http://localhost:8081/validate?token=" + token);
        return result.equals("true");
    }


}
