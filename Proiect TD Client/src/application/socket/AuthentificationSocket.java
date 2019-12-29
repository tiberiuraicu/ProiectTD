package application.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class AuthentificationSocket {
	Socket socket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;

	public AuthentificationSocket() {

	}

	public String loginUser(String loginUsername, String loginPassword)
			throws IOException, ClassNotFoundException, InterruptedException {
		InetAddress host;
		try {
			host = InetAddress.getLocalHost();

			// establish socket connection to server
			socket = new Socket(host.getHostName(), 9876);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());

		oos.writeObject(loginUsername + "," + loginPassword + ",login");

		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		String loginResult = (String) ois.readObject();

		// close resources
		ois.close();
		oos.close();
		Thread.sleep(100);

		return loginResult;
	}

	public String registerUser(String registerUsername, String registerPassword)
			throws IOException, ClassNotFoundException, InterruptedException {
		InetAddress host;
		try {
			host = InetAddress.getLocalHost();

			// establish socket connection to server
			socket = new Socket(host.getHostName(), 9876);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());

		oos.writeObject(registerUsername + "," + registerPassword + ",register");

		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		String registerResult = (String) ois.readObject();

		// close resources
		ois.close();
		oos.close();
		Thread.sleep(100);

		return registerResult;
	}

	public String getAllUsersAndEvents() throws IOException, ClassNotFoundException, InterruptedException {

		InetAddress host;
		try {
			host = InetAddress.getLocalHost();

			// establish socket connection to server
			socket = new Socket(host.getHostName(), 9878);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());

		oos.writeObject(" " + ",getAll");

		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		String registerResult = (String) ois.readObject();

		System.out.println(registerResult);

		// close resources
		ois.close();
		oos.close();
		Thread.sleep(100);

		return registerResult;
	}

	public void createEvent(String objectEvent) throws IOException, ClassNotFoundException, InterruptedException {

		InetAddress host;
		try {
			host = InetAddress.getLocalHost();

			// establish socket connection to server
			socket = new Socket(host.getHostName(), 9878);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());

		oos.writeObject(objectEvent);

		// close resources
	
		oos.close();
		Thread.sleep(100);
//TODO create return method
	}
}
