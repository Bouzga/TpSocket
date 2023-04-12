package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMT extends Thread {
    private boolean isActive=true;
    private int nombreClient=0;
    public static void main(String[]args) {
        new ServerMT().start();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket=new ServerSocket(1234);
            while(isActive){
                Socket socket=serverSocket.accept();
                ++nombreClient;
                new Conversation(socket,nombreClient).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    class Conversation extends Thread{
        private Socket socketClient;
        private int numero;
        public Conversation(Socket socketClient,int numero){
               this.socketClient=socketClient;
               this.numero=numero;
        }

        @Override
        public void run() {
            try {
                InputStream inputStream=socketClient.getInputStream();
                InputStreamReader isr=new InputStreamReader(inputStream);
                BufferedReader br=new BufferedReader(isr);

                PrintWriter pw=new PrintWriter(socketClient.getOutputStream(),true);
                String ipClient= socketClient.getRemoteSocketAddress().toString();
                pw.println("Bienvenue vous etes le client numero:"+numero);
                System.out.println("Connexion du client numero:"+numero+",IP="+ipClient);

                while (true){
                    String req=br.readLine();
                    String response="Length="+req.length();
                    pw.println(response);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
