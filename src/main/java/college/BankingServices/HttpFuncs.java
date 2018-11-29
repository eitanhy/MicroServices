package college.BankingServices;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Eitan on 4/14/2018.
 */
public class HttpFuncs {

    public static String getResponse(String url){
        try {
            URL uri = new URL(url);
            HttpURLConnection connection =
                    null;
            try {
                connection = (HttpURLConnection) uri.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder result = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        catch (MalformedURLException me){
            me.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
