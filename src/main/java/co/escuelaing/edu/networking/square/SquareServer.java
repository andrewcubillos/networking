
package co.escuelaing.edu.networking.square;

import java.net.*;
import java.io.*;

public class SquareServer {
    private static String square(String num){
        int sq = (int) Math.pow((Integer.parseInt(num)),2);
        return String.valueOf(sq);
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
            outputLine = "El cuadrado del numero " + inputLine;
            if (outputLine.equals("El cuadrado del numero Bye.")) {
                break;
            }
            out.println(outputLine + ", es: " + square(inputLine));
            }
        System.out.println("Cerrando el servidor...");
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
