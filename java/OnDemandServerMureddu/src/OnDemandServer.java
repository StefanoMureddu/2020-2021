
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Stefano
 */
public class OnDemandServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket server;
        try{
            server = new ServerSocket(5555);
            while(true){
                Socket client = server.accept();
                new Thread(new ServerThread(client)).start();
            }
        }catch(IOException ex){}
    }
    
}
