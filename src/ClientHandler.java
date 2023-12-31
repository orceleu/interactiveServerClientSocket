import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements  Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;
    public  ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException {

        this.client = clientSocket;
        this.clients=clients;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);
    }

    @Override
    public void run() {
        try{
            while (true){
                String request=in.readLine();
                if (request.contains("name")){
                    out.println(Server.getRandomName());
                } else if (request.startsWith("say")){
                    int firstSpace= request.indexOf("");
                    if (firstSpace!= -1){
                        outToAll(request.substring(firstSpace +3 ));
                    }

                }

                else {
                    out.println("tell me 'name' to get a random name.");
                }

            }


        }catch (IOException e){
          System.err.println("IOException in clientHandler");
          System.err.println(e.getStackTrace());

        }


            finally {

            out.close();
            try{
                in.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    private void outToAll(String msg) {

        for(ClientHandler aClaent : clients){
            aClaent.out.println(msg);
        }
    }
}
