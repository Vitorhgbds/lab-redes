// Recebe um pacote de algum cliente
// Separa o dado, o endere�o IP e a porta deste cliente
// Imprime o dado na tela

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
   private static byte[] receiveData;
   private static DatagramSocket serverSocket;
   private static UDPServer instance;

   private UDPServer(int maxDataLength, int port) throws Exception {
      // cria socket do servidor com a porta 9876
      this.serverSocket = new DatagramSocket(port);
      this.receiveData = new byte[maxDataLength];
   }

   public static UDPServer getInstance(int maxDataLength, int port) throws Exception {
      if (instance == null) {
         instance = new UDPServer(maxDataLength, port);
      }
      return instance;
   }

   public static void run() throws Exception {
      while (true) {
         // declara o pacote a ser recebido
         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

         // recebe o pacote do cliente
         serverSocket.receive(receivePacket);

         // pega os dados, o endere�o IP e a porta do cliente
         // para poder mandar a msg de volta
         String sentence = new String(receivePacket.getData());
         InetAddress IPAddress = receivePacket.getAddress();
         int port = receivePacket.getPort();

         System.out.println("Mensagem recebida: " + sentence);
      }
   }

}