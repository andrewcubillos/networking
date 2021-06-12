
package co.escuelaing.edu.networking.trigfunctions;

import java.net.*;
import java.io.*;

public class TrigFuncServer {
    static String funcion = "cos";
    private static String Function(String num){
        String fun;
        try {
        
        
        double fn=Math.toRadians(((Double.parseDouble(num))));
        System.out.println(fn);
       
        if(funcion.contains("sin")){
           
            fn=Math.sin(fn);
        }
        else if (funcion.contains("cos")){
           
            fn=Math.cos(fn);
        }
        else if (funcion.contains("tan")){
           
            fn=Math.tan(fn);
        }
        else System.out.println("Función Incorrecta");
        fun=funcion+" : "+ String.valueOf(fn);
        }
        catch(Exception e){
            fun="Digite el número";
        }
        return fun;
    }
    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
            }
        Socket clientSocket = null;
        try {
            System.out.println("Listo para recibir en puerto 3500");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
            }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(
            "Mensaje recibido:" + inputLine);
            outputLine = inputLine;
            if (outputLine.equals("Bye.")) {
                break;
            }
            else if(outputLine.indexOf("fun:")!=-1){
                funcion=((outputLine.split(":"))[1]).replaceAll("\n", "");
                
                
                
            }
            out.println(Function(inputLine));
            }
        System.out.println("Cerrando el servidor...");
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
