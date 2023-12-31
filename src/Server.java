import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    private  static String[] names={"Mily","Claude","Michael","jean"};
    private  static String[] adjs={"the gentle","the un-gentle","Mthe urbane","the overwrought"};

    private  static final int PORT =9090;


    public static void main(String[] args) throws IOException {

        ServerSocket listener= new ServerSocket(PORT);
        System.out.println("[Server] waiting for connection...");
        Socket client=listener.accept();
        System.out.println("[Server] connected to the client!");
        PrintWriter out =new PrintWriter(client.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

      try{
        while(true) {
            String request = in.readLine();
            if (request.contains("name")) {
                out.println(getRandomName());
            } else {
                out.println("tell me a name to get a random name.");
            }
        }

        }finally {
          out.close();
          in.close();
      }

       /* System.out.println("[Server] Name sent. Closing.");
        listener.close();
        client.close();*/

    }

    private static String getRandomName() {
        String name=names[(int) (Math.random()+ names.length -1)];
        String adj=adjs[(int) (Math.random()+ adjs.length -1)];
        return  name +" "+ adj;

    }
}