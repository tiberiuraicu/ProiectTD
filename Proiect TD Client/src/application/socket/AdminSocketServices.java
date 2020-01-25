package application.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class AdminSocketServices {

	Socket socket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;

	public AdminSocketServices() {

		InetAddress host;
		try {
			host = InetAddress.getLocalHost();

			// establish socket connection to server
			socket = new Socket(host.getHostName(), 9878);

			// write to socket using ObjectOutputStream
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getAllUsersAndEvents() throws IOException, ClassNotFoundException, InterruptedException {

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

		oos.writeObject(objectEvent);

		// close resources

		oos.close();
		Thread.sleep(100);
//TODO create return method
	}
}
