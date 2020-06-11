package edu.escuelaing.arsw.intro.app.tallernetworking.Http;

/**
 *
 * @author Eduard Jimenez.
 */
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import javax.imageio.ImageIO;

public class HttpServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(32000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 32000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        while (true) {
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out;
            BufferedReader in;

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine, res = "";
            int contador = 0;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (contador == 0) {
                    res = inputLine;
                }

                contador++;
                if (!in.ready()) {
                    break;
                }
            }
            outputLine = "";
            if (res.substring(0, 3).equals("GET")) {
                BuscarArchivo find = new BuscarArchivo();
                File archivoEncontrado = find.buscador(
                        res.substring(5, res.length() - 9),
                        new File("C:\\Users\\Z470\\Documents\\NetBeansProjects\\TallerNetworking\\src\\main"));
                if (archivoEncontrado != null) {

                    BufferedReader reader = new BufferedReader(new FileReader(archivoEncontrado));
                    StringBuilder cadena = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        cadena.append(line);
                    }
                    
                    BufferedImage image = ImageIO.read(new File("C:\\Users\\Z470\\Documents\\NetBeansProjects\\TallerNetworking\\src\\main\\resources\\img\\suzaku.png"));
                    
                    out.println("HTTP/1.1 200 OK");
                    
                    if(res.substring(res.length() - 12,  res.length() - 9).toUpperCase().equals("PNG")){
                        out.println("Content-Type: image/png");
                        out.println(); 
                        ImageIO.write(image, "PNG", clientSocket.getOutputStream());
                        
                    }else{
                        out.println("Content-Type: text/html");
                        out.println(); 
                        out.println(cadena);
                    }

                    

                } else {
                    outputLine = error(outputLine,res);
                    out.println(outputLine);
                }
            }

            out.close();
            in.close();
        }

    }

    private static String error(String outputLine, String res) {
       
        outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Title of the document</title>\n"
                + "</head>"
                + "<body>"
                + "<h1>ERROR 404.<p><div style='color:red'>" + res.substring(5, res.length() - 9).toUpperCase() + "</div>" + " NO ENCONTRADO</p></h1>"
                + "</body>"
                + "</html>";
        return outputLine;
    }
}
