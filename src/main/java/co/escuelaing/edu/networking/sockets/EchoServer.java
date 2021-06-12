//hecho en clase
package co.escuelaing.edu.networking.sockets;

import java.net.*;
import java.io.*;

public class EchoServer {

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
            outputLine = "Respuesta de servidor: " + inputLine;
            out.println(outputLine);
            if (outputLine.equals("Respuesta de servidor: Bye.")) {
                break;
            }
            }
        System.out.println("Cerrando el servidor...");
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
