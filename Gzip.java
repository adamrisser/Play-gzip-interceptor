package controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import play.mvc.Controller;
import play.mvc.Finally;

public class Gzip extends Controller {
    
    @Finally
    static void compress() throws IOException {
        if (request.headers.containsKey("accept-encoding") && 
            request.headers.get("accept-encoding").toString().contains("gzip")) {
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            
            gzip.write(response.out.toByteArray());
            gzip.close();
            
            response.setHeader("Content-Encoding", "gzip");
            response.out = out;
       }
    }
}