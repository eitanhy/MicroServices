package college.BankingServices.Cache;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by Eitan on 3/17/2018.
 */
@RestController
public class CachingServiceController {


    @GetMapping(path = "/token")
    public static String generateToken(@RequestParam(name="userId") String userId){

        String token = UUID.randomUUID().toString();
        CachingService.commands.append(token, userId);
        CachingService.commands.expire(token,600000);
        return token;
    }

    @PostMapping(path = "/token/remove")
    public static void removeToken(@RequestParam(name="token")String token){
        CachingService.commands.del(token);
    }


    @GetMapping(path = "/token/isExistent")
    public static boolean isTokenExistent(@RequestParam(name="token")String token){
        return CachingService.commands.exists(token);
    }

    @GetMapping(path = "/token/canExecute")
    public static boolean canTokenExecute(@RequestParam(name="token")String token,@RequestParam(name="userId") String userId){
        try {
            return CachingService.commands.get(token).equals(userId);
        }
        catch (Exception e){
            return false;
        }

    }
}
