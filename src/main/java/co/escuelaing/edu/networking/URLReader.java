/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.escuelaing.edu.networking;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * desarrollado en clase
 * 
 */
public class URLReader {
    public static void main(String... args) throws MalformedURLException{
        URL personalSite = new URL("http://ldbn.escuelaing.edu.co:80/publicaciones/publicaciones.pdf?val=45&r=78#publicaciones");
        System.out.println("Authority: " + personalSite.getAuthority());
        System.out.println("Host: " + personalSite.getHost());
        System.out.println("Port: " + personalSite.getPort());
        System.out.println("Path: " + personalSite.getPath());
        System.out.println("Query: " + personalSite.getQuery());
        System.out.println("File: " + personalSite.getFile());
        System.out.println("Ref: " + personalSite.getRef());
        
    }
    
}
