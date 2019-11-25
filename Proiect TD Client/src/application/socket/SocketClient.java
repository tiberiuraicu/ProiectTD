package application.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class implements java socket client
 * 
 * @author pankaj
 *
 */
public class SocketClient {
	Socket socket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;



	public void loginUser(String loginUsername, String loginPassword)
			throws IOException, ClassNotFoundException, InterruptedException {
		InetAddress host = InetAddress.getLocalHost();

		// establish socket connection to server
		socket = new Socket(host.getHostName(), 9876);
		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("Sending request to Socket Server");
		oos.writeObject(loginUsername + "," + loginPassword);
		// read the server response message

		ois = new ObjectInputStream(socket.getInputStream());
		String message = (String) ois.readObject();
		System.out.println("Message: " + message);
		// close resources
		ois.close();
		oos.close();
		Thread.sleep(100);

	}

	public void registerUser(String registerUsername, String registerPassword)
			throws IOException, ClassNotFoundException, InterruptedException {
		InetAddress host = InetAddress.getLocalHost();

		// establish socket connection to server
		socket = new Socket(host.getHostName(), 9876);
		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("Sending request to Socket Server");
		oos.writeObject(registerUsername + "," + registerPassword);
		// read the server response message

		ois = new ObjectInputStream(socket.getInputStream());
		String message = (String) ois.readObject();
		System.out.println("Message: " + message);
		// close resources
		ois.close();
		oos.close();
		Thread.sleep(100);

	}
}