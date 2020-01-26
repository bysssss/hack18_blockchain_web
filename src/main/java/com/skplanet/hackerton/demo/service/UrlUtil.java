package com.skplanet.hackerton.demo.service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class UrlUtil {

    private static boolean isSSL(String url) {
        if( url.contains("http://")) {
            return false;
        } else if( url.contains("https://")) {
            return true;
        } else {
            return false;
        }
    }
    public static String request(HttpMethod method, String url, String body) {
        StringBuffer responseBody = new StringBuffer();

        HttpURLConnection conn = null;
        try {
            // 1) HTTP Connect
            URL urlObject = new URL(url);
            if( UrlUtil.isSSL(url)) {
                conn = (HttpsURLConnection) urlObject.openConnection();
            } else {
                conn = (HttpURLConnection) urlObject.openConnection();
            }
            conn.setRequestMethod(method.name());
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);
            conn.setConnectTimeout(6000);
            conn.setReadTimeout(6000);

            // 2) HTTP Request
            if( body != null) {
                BufferedWriter outStreamWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                outStreamWriter.write(body);
                outStreamWriter.close();
                conn.getOutputStream().flush();
                conn.getOutputStream().close();
            }
            boolean isOk = true;
            int statusCode = conn.getResponseCode();
            HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
            switch(httpStatus) {
                case OK:
                    isOk = true;
                    break;
                case BAD_REQUEST:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case METHOD_NOT_ALLOWED:
                case UNSUPPORTED_MEDIA_TYPE:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                    isOk = false;
                    break;
                default:
                    throw new IllegalArgumentException(httpStatus.toString());
            }

            // 3) HTTP Response
            InputStream inputStream = null;
            if(isOk) {
                inputStream = conn.getInputStream();
            } else {
                inputStream = conn.getErrorStream();
            }
            BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            for( String line=null; (line=inputStreamReader.readLine()) != null;) {
                responseBody.append(line);
            }
            inputStreamReader.close();
            inputStream.close();
        } catch (IllegalArgumentException | IOException e) {
            return null;
        } finally {
            // 4) HTTP Disconnect
            if( conn != null) {
                conn.disconnect();
            }
        }

        // 5) return
        return responseBody.toString();
    }
}
