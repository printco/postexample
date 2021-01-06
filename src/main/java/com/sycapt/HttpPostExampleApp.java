package com.sycapt;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/***********************************Request****************************************
POST /postexample.php HTTP/1.1
User-Agent: PostmanRuntime/7.26.8
Postman-Token: 80ac1b52-866f-44bf-9ba9-19ea5723629b
Host: localhost
Accept-Encoding: gzip, deflate, br
Connection: keep-alive
Content-Type: application/x-www-form-urlencoded
Content-Length: 62

filename%5B%5D=a.txt&filename%5B%5D=b.txt&filename%5B%5D=c.txt

 **********************************Response****************************************
HTTP/1.1 200 OK
Date: Wed, 06 Jan 2021 06:54:02 GMT
Server: Apache/2.4.33 (Win32) OpenSSL/1.0.2o PHP/7.0.33
X-Powered-By: PHP/7.0.33
Content-Length: 130
Keep-Alive: timeout=5, max=100
Connection: Keep-Alive
Content-Type: text/html; charset=UTF-8

This is response from server


 */


public class HttpPostExampleApp {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://localhost/postexample.php");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        // Post method
        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);

        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // HTTP post data
        OutputStream os = con.getOutputStream();
        // Remote upload files to sftp
        // Array of file
        // (
        //      [0] => a.txt
        //      [1] => b.txt
        //      [2] => c.txt
        // )
        String payload = "filename[]=a.txt&filename[]=b.txt&filename[]=c.txt&destpath=/home/user/upload/";
        os.write(payload.getBytes());


        InputStreamReader isr = new InputStreamReader( con.getInputStream() , "UTF-8");
        StringBuilder response = new StringBuilder();
        BufferedReader br = new BufferedReader(isr);

        String httpResponseContent = "";
        while( (httpResponseContent = br.readLine()) != null ) {
            response.append(httpResponseContent);
        }
        System.out.println(response.toString());
        con.disconnect();

    }
}
