package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {
    public static void main(String[]args) throws IOException {
        System.out.println("Je me connecte au serveur");
        Socket socket = new Socket("localhost",1234);
        InputStream is=socket.getInputStream();
        OutputStream os=socket.getOutputStream();
        //Saisir un nbr
        Scanner scanner=new Scanner(System.in);
        System.out.println("Donner un nombre:");
        int nb=scanner.nextInt();

        System.out.println("J'envoie le nombre"+nb+"au serveur");
        os.write(nb);
        System.out.println("J'attens la reponse du serveur...");
        int rep=is.read();
        System.out.println("Reponse du serveur est:"+rep);
    }
}
