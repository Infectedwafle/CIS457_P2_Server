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
	    if (!checkDomainRequest(message)){
	      // Send the client the RCODE that the request is not
	      // supported.
	      System.out.println("Not a supported domain extension request from client");
	      message = message + " is not a supported domain extension";
	      byte[] sendData = message.getBytes();
	      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
	      serverSocket.send(sendPacket);
	    }
	    // else we send the request to a root name server. 
	    else {
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
	}
	/* This method checks the type and class of the request.
	*  If the type is other than A and the class is other than
	*  IN the method should return false;
	*/
	public static boolean checkDomainRequest(String m) throws FileNotFoundException{
	  List<String> domainExtensions = new ArrayList<String>();    
	  File text = new File("domain_extensions.txt");
	  Scanner fileScanner = new Scanner(text);
	  while (fileScanner.hasNext()){
	    domainExtensions.add(fileScanner.next());
	  }
	  int index = m.lastIndexOf('.'); 
	  String temp = m.substring(index+1);
	  if (domainExtensions.contains(temp.toUpperCase())){
	    return true;
	  }
	  return false;
	}
}