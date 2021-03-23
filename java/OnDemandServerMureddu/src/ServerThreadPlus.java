
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.in;
import static java.lang.System.out;
import java.net.Socket;

/**
 *
 * @author Stefano
 */
public class ServerThreadPlus implements Runnable{
    
    Socket client = null;
    Socket clientTwo = null;
    
    public ServerThreadPlus(Socket client){
        this.client = client;
    }
    
    public void addClient(){
        
    }

    public void run() {
        try {
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            int read = 0;
            while((read = in.read()) != -1){
                out.write(read);
            }
            out.close();
            in.close();
            client.close();
        } catch (IOException ex) {}
    }

}




