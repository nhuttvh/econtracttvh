/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package econtractapi.utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Admin
 */
public class JSONUtil {
    public static String Unescape(String jsonSTR)
    {
        return org.apache.commons.text.StringEscapeUtils.unescapeJson(jsonSTR);
    }
    public static String API(String v_url, String inputJson)
    {
        String v_return = "faile";
        try
        {
            URL url = new URL (v_url);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            //String jsonInputString = inputJson;
            //String jsonInputString = "{\n" +
            //                        "  \"hash_key\":\"fb77004170428200428486ba33c84a47317981a21affccad3e58b61ea87a9c69\",\n" +
            //                        "  \"ma_bien_nhan\":\"29254202262\", \n" +
            //                        "  \"ma_phuong_xa\":\"29254\",\n" +
            //                        "  \"ma_can_bo_chuyen\":133,\n" +
            //                        "  \"ma_can_bo_nhan\":133,\n" +
            //                        "  \"tra_ket_qua\":3,\n" +
            //                        "  \"y_kien_xu_ly\":\"\",\n" +
            //                        "  \"ngay_chuyen\":\"09-09-2020-14-24-55\"\n" +
            //                        "}";
            try(OutputStream os = con.getOutputStream()) 
            {
                byte[] input = inputJson.getBytes("utf-8");
                os.write(input, 0, input.length);			
            }
            try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) 
            {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                v_return = JSONUtil.Unescape(response.toString());
                System.out.println(v_return);
            }
        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
        } 
        catch (IOException ex) {
            Logger.getLogger(HttpUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v_return;
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
            e.printStackTrace();
        }
    }  
    public static String getElementJSON(String jsonSTR, String ele)
    {
        String ret = "";
        try 
        {
            JSONObject jsonObject = new JSONObject(jsonSTR);
            ret = jsonObject.getString(ele);
        } catch (JSONException ex) {
            Logger.getLogger(JSONUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    public static String getElementJSONArray(String jsonSTR, String ele)
    {
        String ret = "";
        try 
        {
            JSONArray jsonObjects = new JSONArray(jsonSTR);
            for (int i = 0; i < jsonObjects.length(); i++) 
            {
                JSONObject jsonObject = jsonObjects.getJSONObject(i);
                ret = jsonObject.getString(ele);
            }
        } catch (JSONException ex) {
            Logger.getLogger(JSONUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}
