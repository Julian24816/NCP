package w42;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class GoodUDPHelloWorld {

    private String message = "Hello World";

    private DatagramSocket udpSocket = null;

    private String IPDest = "127.0.0.1"; // loop back IP address = self-sending

    private int UDPLocalPort = 50000; // free dynamic port
    private int UDPRemotePort = UDPLocalPort; //because sending message to myself

    public static void main(String[] args) {
        new GoodUDPHelloWorld().run();
    }

    private void run() {
        try {
            initSocket();
            sendMessage();
            waitToReceiveMessage();
            closeSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initSocket() throws Exception {
        udpSocket = new DatagramSocket(UDPLocalPort); // binding network port
    }

    private void closeSocket() {
        udpSocket.close(); // always close after use to allow other programs to use it
    }

    private void sendMessage() throws Exception {
        byte[] data = message.getBytes();

        InetAddress dest = InetAddress.getByName(IPDest);
        DatagramPacket packToSend = new DatagramPacket(data, data.length, dest, UDPRemotePort);

        udpSocket.send(packToSend); // blocked until send!

        System.out.println("Send: " + new String(packToSend.getData()));
    }

    private void waitToReceiveMessage() throws Exception {
        int messageLength = message.getBytes().length;
        byte[] dataBuffer = new byte[messageLength];

        DatagramPacket packToRecieve = new DatagramPacket(dataBuffer, messageLength);

        udpSocket.receive(packToRecieve); // blocked until receive something!

        System.out.println("Received: " + new String(packToRecieve.getData()));
    }

}
