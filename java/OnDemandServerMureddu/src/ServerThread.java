
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Stefano
 */
public class ServerThread implements Runnable{
    
    Socket client = null;
    
    public ServerThread(Socket client){
        this.client = client;
    }

    public void run() {
        try {
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            Path log = Paths.get("pagina/log.txt");
            List<String> logsLines = Files.readAllLines(log);
            String currentLog ="";
            int read = 0;
            int ric = 0;
            char car = 's';
            String str = "";
            while(read != -1){
                ric = in.read();
                car = (char) ric;
                str += car;
                if(car == '\n'){
                    read = -1;
                }
            }
 
            String[] rec = str.split(" ");
            if(rec[0].equals("GET")){
                try{
                    Path file = Paths.get("pagina",rec[1].trim());
                    file.resolve(file);
                    file = file.normalize();
                    Path inizio = Paths.get("pagina");
                    System.out.println(file);
                    String tipo = Files.probeContentType(file);
                    String start 
                            = "HTTP/1.1 200 OK\nServer:localhost\nContent-Type: "+tipo+"\n\n";
                    if(Files.exists(file) && !Files.notExists(file)){
                        if(file.startsWith(inizio)){
                            out.write(start.getBytes()); 
                            out.write(Files.readAllBytes(file)); 
                            currentLog = LocalDateTime.now() +
                                " richiesto il file "+file.toString()+
                                " con risultato 200";
                        }else{
                            currentLog = LocalDateTime.now() +
                                " richiesto il file "+file.toString()+
                                " con risultato 403";
                        } 
                    }else{
                        currentLog = LocalDateTime.now() +
                                " richiesto il file "+file.toString()+
                                " con risultato 404";
                        file = Paths.get("pagina/errore.html");
                        out.write(start.getBytes()); 
                        out.write(Files.readAllBytes(file));
                    }
                }catch(Exception er){
                    currentLog = LocalDateTime.now() +
                                " la richiesta ha generato un errore";
                }
            }
            logsLines.add(currentLog);
            Files.write(log, logsLines);
            out.close();
            in.close();
            client.close();
        } catch (IOException ex) {}
    }

}




