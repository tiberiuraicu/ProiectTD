package application.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class InvitationsSocketServices {

	Socket socket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;

	public InvitationsSocketServices() {
		try {
			InetAddress host = InetAddress.getLocalHost();

			// establish socket connection to server
			socket = new Socket(host.getHostName(), 9877);

			// write to socket using ObjectOutputStream
			oos = new ObjectOutputStream(socket.getOutputStream());
			//oos.writeObject("ready");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String waitForInvitations() throws IOException, ClassNotFoundException {
		
		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());

		return (String) ois.readObject();
	}

	public String confirmSpotOnEvent(String confirmSpotOnEventObject) throws IOException, ClassNotFoundException {
		try {
			InetAddress host = InetAddress.getLocalHost();

			// establish socket connection to server
			socket = new Socket(host.getHostName(), 9877);

			// write to socket using ObjectOutputStream
			oos = new ObjectOutputStream(socket.getOutputStream());
			//oos.writeObject("ready");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oos.writeObject(confirmSpotOnEventObject);

		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());

		return (String) ois.readObject();
	}

	public String inviteUser(String invitationString) throws IOException, ClassNotFoundException {
		try {
			InetAddress host = InetAddress.getLocalHost();

			// establish socket connection to server
			socket = new Socket(host.getHostName(), 9877);

			// write to socket using ObjectOutputStream
			oos = new ObjectOutputStream(socket.getOutputStream());
			//oos.writeObject("ready");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oos.writeObject(invitationString);

		// read the server response message
		ois = new ObjectInputStream(socket.getInputStream());

		return (String) ois.readObject();
	}
}
