
package edu.escuelaing.arsw.intro.app.tallernetworking.Http;

/**
 *
 * @author Eduard Jimenez.
 */
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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

                    Path archivo = Paths.get(archivoEncontrado.toString());
                    BufferedReader lector = Files.newBufferedReader(archivo, Charset.forName("UTF-8"));
                    BufferedImage image = ImageIO.read(new File("C:\\Users\\Z470\\Documents\\NetBeansProjects\\TallerNetworking\\src\\main\\resources\\img\\lluvia.jpg"));

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ImageIO.write(image, "PNG", byteArrayOutputStream);
                    
                    byteArrayOutputStream.flush();
                    
                    String base64String=Base64.encode(byteArrayOutputStream.toByteArray());
                    byteArrayOutputStream.close();
                    
                    byte[] bytearray = Base64.decode(base64String);
 
                    BufferedImage imag=ImageIO.read(new ByteArrayInputStream(bytearray));

                    imag.getGraphics().drawImage(image, 0, 0, null);
                    
                    outputLine = "HTTP/1.1 200 OK\r\n"
                            + "Content-Type: image/png\r\n"
                            + "\r\n"
                            + cadena;


                } else {
                    out.write(-1);
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
                }
            }

            out.println(outputLine);
            out.close();
            in.close();
        }

    }
}
