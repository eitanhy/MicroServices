package college.BankingServices.services;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.net.www.http.HttpClient;

import javax.xml.bind.JAXBContext;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Eitan on 3/17/2018.
 */

@RestController
public class BankingServicesController {
    private static String dbName = "brit_bank";

    @GetMapping(path = "/query/login")
    public boolean queryLogin(@RequestParam(name="userName")String userName, @RequestParam(name="pass")String pass) throws IOException, ParseException {
        HashMap<String,String> searchParams = new HashMap<String,String>();
        searchParams.put("account_name",userName);
        searchParams.put("password",pass);
        JSONObject result = (JSONObject) queryDBService(searchParams, null);
        return (((JSONArray)result.get("docs")).size() > 0);
    }

    public Object queryDBService(HashMap<String,String> searchParams,HashSet fieldResults) throws IOException, ParseException {
        URL uri =
                new URL("http://localhost:5984/brit_bank/_find");
        HttpURLConnection connection =
                null;

        try {
            connection = (HttpURLConnection) uri.openConnection();
            connection.setRequestMethod(RequestMethod.POST.toString());
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        JSONObject selector = new JSONObject();
        JSONArray fields = new JSONArray();
        for (String key : searchParams.keySet()){
            selector.put(key,searchParams.get(key));
        }
        jsonObject.put("selector",selector);
        if (fieldResults != null) {
            for (Object value : fieldResults) {
                fields.add(value);
            }
        }
        if (fields.size() > 0) {
            jsonObject.put("fields", fields);
        }


        OutputStream os = connection.getOutputStream();
        os.write(jsonObject.toJSONString().getBytes());

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                result.append(line);
            }
            JSONParser parser = new JSONParser();
            return parser.parse(result.toString());
        }
        else {
            return new JSONObject();
        }
    }
}
