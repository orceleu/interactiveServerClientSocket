import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {


    private  static String[] names={"Mily","Claude","Michael","jean"};
    private  static String[] adjs={"the gentle","the un-gentle","Mthe urbane","the overwrought"};

    private  static final int PORT =9090;

private static ArrayList<ClientHandler> clients =new ArrayList<>();
private static ExecutorService pool = Executors.newFixedThreadPool(4);


    public static void main(String[] args) throws IOException {

        ServerSocket listener= new ServerSocket(PORT);
        while(true) {
            System.out.println("[Server] waiting for connection...");
            Socket client = listener.accept();
            System.out.println("[Server] connected to the client!");
           ClientHandler clientThread=new ClientHandler(client,clients);
           clients.add(clientThread);
           pool.execute(clientThread);
        }
    }

    public static String getRandomName() {

        Random rand = new Random();
        Random rand2 = new Random();
        int indexAleatoire=rand.nextInt(names.length);
        int indexAleatoire2=rand2.nextInt(names.length);
        String nomAl=names[indexAleatoire];
        String adj=adjs[indexAleatoire2];


        /*String name=names[(int) (Math.random()+ names.length -1)];
        String adj=adjs[(int) (Math.random()+ adjs.length -1)];*/
        return  nomAl +" "+ adj;

    }
}