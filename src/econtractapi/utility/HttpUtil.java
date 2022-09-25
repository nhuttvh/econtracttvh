/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package econtractapi.utility;

/**
 *
 * @author CUI BAP
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class HttpUtil {

    public static void saveToFile(String address, String localFileName, String code) throws IOException {
        File file = new File(localFileName+"\\Ebook"+code+".rar");
        Connection connection = Jsoup.connect(address).ignoreContentType(true).ignoreHttpErrors(true);
        connection.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
        connection.timeout(10000);
        Connection.Response resultImageResponse = connection.execute();
        FileOutputStream out = (new FileOutputStream(file));
        out.write(resultImageResponse.bodyAsBytes());           // resultImageResponse.body() is where the image's contents are.
        out.close();
    }
    
    public static String getCode(String address)
    {
        return address.substring(address.lastIndexOf("/")+1);
    }
    public static void postAPI(String url)
    {
        try
        {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            String urlParameters = "..";
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                res.append(inputLine);
            }
            System.out.println(res);
            in.close();
        }
        catch(IOException e)
        {
            
        }
    }
}