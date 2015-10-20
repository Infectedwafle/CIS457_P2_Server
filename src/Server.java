import java.io.*;
import java.net.*;
import java.util.Random;

class Server {
	public static void main(String args[]) throws Exception {
	  DatagramSocket serverSocket = new DatagramSocket(9876);
	  while (true) {
	    byte[] receiveData = new byte[1024];
	    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	    serverSocket.receive(receivePacket);
	    String message = new String(receiveData);
	    InetAddress IPAddress = receivePacket.getAddress();
	    int port = receivePacket.getPort();
	    System.out.println("Got message: " + message);
	    //Need to check the message.
	    /*
	    if (!checkTypeClass()){
	      // Send the client the RCODE that the request is not
	      // supported.
	    }
	    // else we send the request to a root name server. 
	    */
	    Random r = new Random();
	    Header header = new Header();
	    header.setId((short) r.nextInt(65536));
	    header.setRequest(false);
	    header.setOpcode((short) 0); 
	    header.setAuthortativeAnwser(false);
	    header.setTruncated(false);
	    header.setRecursion(true);
	    header.setCanRecurse(false);
	    header.setQuestionEntries((short) 1);
	    
	    
	    
	    DatagramPacket dnsRequest = new DatagramPacket(header.toBytes(buf), message.length(),, 52));
	    
	    
	    System.out.println(IPAddress);
	    
	    byte[] sendData = message.getBytes();
	    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
	    serverSocket.send(sendPacket);
	  }
	}
	/* This method checks the type and class of the request.
	*  If the type is other than A and the class is other than
	*  IN the method should return false;
	*/
	public static boolean checkTypeClass(){
	  return false;
	}
}