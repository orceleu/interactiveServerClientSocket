import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection implements  Runnable {


    private Socket server;
    private BufferedReader in;




    public  ServerConnection(Socket s) throws IOException {

        server=s;
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));

    }


    @Override
    public void run() {




            try { while (true) {
                String   serverReponse = in.readLine();
                if(serverReponse==null)break;

                System.out.println("Server: " + serverReponse);
            }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    in.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }
    }

