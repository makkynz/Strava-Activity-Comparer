package stravacustom.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class HttpHelper {

    public static String Post(String url, List<NameValuePair> params){

        HttpPost httppost = new HttpPost(url);

        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String rawResponse = doRequest(httppost);

        return rawResponse;

    }
    public static String Get(String url, List<NameValuePair> header){

        HttpGet httpGet = new HttpGet(url);
        for (NameValuePair nameValuePair : header) {
            httpGet.setHeader(nameValuePair.getName(),nameValuePair.getValue());
        }


        String rawResponse = doRequest(httpGet);

        return rawResponse;

    }

    private static String doRequest(HttpRequestBase req) {
        HttpClient httpclient = HttpClients.createDefault();
        HttpResponse response;
        String rawResponse = null;

        System.out.printf("HTTP ("+req.getMethod()+") request to " + req.getURI() + "\n");
        //capture response json
        try {
            response = httpclient.execute(req);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                try (InputStream instream = entity.getContent()) {
                    rawResponse = IOUtils.toString(instream);
                }
            }
            System.out.printf("Response: " + rawResponse+ "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rawResponse;
    }
}