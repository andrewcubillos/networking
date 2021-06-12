package co.escuelaing.edu.networking;

import java.io.*;
import java.net.*;
//desarrollado en clase
public class URLScanner {

    
public static void main(String[] args) throws Exception {
        URL google = new URL("http://www.google.com/");
        try (BufferedReader reader
                = new BufferedReader(new InputStreamReader(google.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                try {
                    PrintWriter writer = new PrintWriter("/D:\\Documentos\\NetBeansProjects\\networking\\ejercicio2/resultado.html", "UTF-8");
                    writer.println(inputLine);
               
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            } catch (IOException x) {
            System.err.println(x);
            }
    }

}
