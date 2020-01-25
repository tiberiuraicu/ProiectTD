package application.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class AuthentificationSocketServices {
	Socket socket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;

	public AuthentificationSocketServices() {
		
		InetAddress host;
		try {
			host = InetAddress.getLocalHost();

			// establish socket connection to server
			socket = new Socket(host.getHostName(), 9876);
			
			// write to socket using ObjectOutputStream
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String loginUser(String loginUsername, String loginPassword)
			throws IOException, ClassNotFoundException, InterruptedException {

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

	
}
