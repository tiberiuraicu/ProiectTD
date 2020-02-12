package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class ClientHandler implements Runnable {
	static SocketFunctions socketFunctions = new SocketFunctions();
	Socket socket;
	Vector<ClientHandler> clients;

	public ClientHandler(Socket socket, Vector<ClientHandler> clients) {
		this.socket = socket;
		this.clients = clients;
	}

	ObjectOutputStream oos;

	@Override
	public void run() {
		try {
			System.out.println("Waiting for the client request");
			if (socket.getInputStream() != null) {
				// read from socket to ObjectInputStream object
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				// convert ObjectInputStream object to String
				String message = (String) ois.readObject();
								// create ObjectOutputStream object
				message = socketFunctions.processInviteMessage(message);

				for (ClientHandler clientHandler : clients) {
					clientHandler.write(message);
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			//System.out.println("connection reset");
		}

	}

	public void write(String message) {

		// write object to Socket
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		// close resources
		// ois.close();
		// oos.close();
		// socket.close();

	}
}
