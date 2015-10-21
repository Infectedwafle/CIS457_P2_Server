import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.ByteBuffer;

class Server {
	public static void main(String args[]) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(9876);
		while (true) {
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);
			serverSocket.receive(receivePacket);

			String message = new String(receiveData);
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			// System.out.println("Got message: " + message);
			// Need to check the message.
			/*
			 * if (!checkDomainRequest(message)){ // Send the client the RCODE
			 * that the request is not // supported. System.out.println(
			 * "Not a supported domain extension request from client"); message
			 * = message + " is not a supported domain extension"; byte[]
			 * sendData = message.getBytes(); DatagramPacket sendPacket = new
			 * DatagramPacket(sendData, sendData.length, IPAddress, port);
			 * serverSocket.send(sendPacket); }
			 */
			// else we send the request to a root name server.
			// else {

			ByteBuffer buf = ByteBuffer.wrap(receiveData);
			Header header = new Header();
			header = header.fromBytes(buf);
			Question ques = new Question();
			ques = ques.fromBytes(buf);
			
			System.out.println("Client Request: ");
			System.out.println("Header: ");
			System.out.println(header.toString());
			System.out.println("Question: ");
			System.out.println(ques.toString());
			
			InetAddress address = InetAddress.getByName("192.228.79.201");
			DatagramPacket dnsRequest = new DatagramPacket(message.getBytes(), message.getBytes().length, address, 52);
			System.out.println("\n\nRequest sent to Servers");
			serverSocket.send(dnsRequest);
			
			byte[] rData = new byte[1024];
			DatagramPacket dnsResponse = new DatagramPacket(rData, rData.length);
			serverSocket.receive(dnsRequest);
			System.out.println("\n\nResponse Recieved");
			String message2 = new String(rData);
			//System.out.println(message2);
			
			ByteBuffer rBuf = ByteBuffer.wrap(rData);
			Header rHeader = new Header();
			rHeader = rHeader.fromBytes(rBuf);
			Question rQuestion = new Question();
			rQuestion = rQuestion.fromBytes(rBuf);
			
			System.out.println("Client Request: ");
			System.out.println("Header: ");
			System.out.println(header.toString());
			System.out.println("Question: ");
			System.out.println(rQuestion.toString());
			
			byte[] sendData = rData;
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			System.out.println("Response sent to Client");
			serverSocket.send(sendPacket);
		}
	}

	/*
	 * This method checks the type and class of the request. If the type is
	 * other than A and the class is other than IN the method should return
	 * false;
	 */
	public static boolean checkDomainRequest(String m)
			throws FileNotFoundException {
		List<String> domainExtensions = new ArrayList<String>();
		File text = new File("domain_extensions.txt");
		Scanner fileScanner = new Scanner(text);
		while (fileScanner.hasNext()) {
			domainExtensions.add(fileScanner.next());
		}
		int index = m.lastIndexOf('.');
		String temp = m.substring(index + 1);
		if (domainExtensions.contains(temp.toUpperCase())) {
			return true;
		}
		return false;
	}
}