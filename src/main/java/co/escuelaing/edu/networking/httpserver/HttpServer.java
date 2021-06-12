
package co.escuelaing.edu.networking.httpserver;

import java.net.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HttpServer {
    private static HttpServer _instance = new HttpServer();
    private HttpServer(){}
    private static HttpServer getInstance(){
        return _instance;
    }
    public static void main(String... args) throws IOException{
        HttpServer.getInstance().startServer(args);
    }
    public static void startServer(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        

        processRequest(clientSocket);

        serverSocket.close();
    }
    
    public static void processRequest(Socket clientSocket) throws IOException{
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        String method="";
        String  path="";
        String version="";
        List<String> headers = new ArrayList<String>();
        while ((inputLine = in.readLine()) != null) {
            if (method.isEmpty()){
                String[] requestStrings= inputLine.split(" ");
                method = requestStrings[0];
                path= requestStrings[1];
                version = requestStrings[2];
                System.out.println("Request: "+method+" "+path+" "+version);
            }
            else{
                System.out.println("Header: " + inputLine);
                headers.add(inputLine);
            }
            
            if (!in.ready()) {
                break;
            }
        }
        String responseMsg = createTextResponse(path);
    out.println(responseMsg);
    out.close();

    in.close();

    clientSocket.close();
    }
    public static String createTextResponse(String path){
        Path file = Paths.get("." + path);
        Charset charset = Charset.forName("UTF-8");
        String outmsg = "";
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                outmsg = outmsg + "\r\n"+line;
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return "HTTP/1.1 200 OK\r\n"
                            + "Content-Type: text/html\r\n"
                            + "\r\n"
                            + "<!DOCTYPE html>"
                            + "<html>"
                            + "<head>"
                            + "<meta charset=\"UTF-8\">"
                            + "<title>Title of the document</title>\n" + "</head>"
                            + "<body>"
                            + "My Web Site"
                            + "<img src=\"https://i1.wp.com/hipertextual.com/wp-content/uploads/2020/04/hipertextual-generador-memes-hace-mas-graciosos-que-humanos-aun-tener-sentido-alguno-2020696204.jpg?fit=1200%2C663&ssl=1\">"
                            + "</body>"
                            + "</html>";
    }
    
    
}
