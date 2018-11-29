package college.BankingServices.Auth;

import college.BankingServices.HttpFuncs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.http.HTTPException;

/**
 * Created by Eitan on 3/17/2018.
 */

@RestController
public class BankAuthServiceController {


    @GetMapping(path = "/login")
    public String login(@RequestParam(name="userName")String userName,@RequestParam(name="pass")String pass){
        // if exist
        String uri =
                "http://localhost:1111/query/login?userName="+userName+"&pass="+pass;
        if (HttpFuncs.getResponse(uri).equals("true")){
            return HttpFuncs.getResponse("http://localhost:8087/token?userId=" + userName);
        }
        else {
            throw new HTTPException(400);
        }
    }

    @GetMapping(path = "/validate")
    public String validate(@RequestParam(name="token")String token){
        String uri =
                "http://localhost:8087/token/isExistent?token="+token;
        return  HttpFuncs.getResponse(uri);

    }

    @GetMapping(path ="/authorize")
    public String authorize(@RequestParam(name="token")String token,@RequestParam(name="userId") String userId){
        return HttpFuncs.getResponse("http://localhost:8087/token/canExecute?token="+token+"@userId="+userId);
    }



}
