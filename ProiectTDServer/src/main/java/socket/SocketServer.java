package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;


public class SocketServer {

	// socket server port on which it will listen
	private static int port = 9876;
	static SocketFunctions socketFunctions = new SocketFunctions();

	public static void main(String args[]) throws IOException, ClassNotFoundException {

		Thread auth = new Thread(new Runnable() {
			// static ServerSocket variable
			private ServerSocket serverLogin;

			@Override
			public void run() {
				// create the socket server object
				try {
					serverLogin = new ServerSocket(port);

					// keep listens indefinitely until receives 'exit' call or program terminates
					while (true) {
						System.out.println("Waiting for the client request");
						// creating socket and waiting for client connection
						Socket socket = serverLogin.accept();
						// read from socket to ObjectInputStream object
						ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
						if (ois != null) {
							// convert ObjectInputStream object to String
							String message = (String) ois.readObject();
							System.out.println("Message Received: " + message);
							// create ObjectOutputStream object
							message = socketFunctions.processMessage(message);
							ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
							// write object to Socket
							oos.writeObject(message);
							// close resources
							ois.close();
							oos.close();
							socket.close();
							// terminate the server if client sends exit request
							if (message.equalsIgnoreCase("exit"))
								break;
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		auth.start();
//TODO make a client handler for every thread
		Thread adminFunctions = new Thread(new Runnable() {
			// static ServerSocket variable
			private ServerSocket serverLogin;

			@Override
			public void run() {
				// create the socket server object
				try {
					serverLogin = new ServerSocket(9878);

					// keep listens indefinitely until receives 'exit' call or program terminates
					while (true) {
						System.out.println("Waiting for the client request");
						// creating socket and waiting for client connection
						Socket socket = serverLogin.accept();
						// read from socket to ObjectInputStream object
						ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
						// convert ObjectInputStream object to String
						String message = (String) ois.readObject();
						System.out.println("Message Received: " + message);
						// create ObjectOutputStream object
						message = socketFunctions.processMessage(message);
						ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
						// write object to Socket
						oos.writeObject(message);
						// close resources
						ois.close();
						oos.close();
						socket.close();
						// terminate the server if client sends exit request
						if (message.equalsIgnoreCase("exit"))
							break;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		adminFunctions.start();

		Thread invitations = new Thread(new Runnable() {
			Vector<ClientHandler> clients = new Vector<ClientHandler>();
			// static ServerSocket variable
			private ServerSocket serverLogin;

			@Override
			public void run() {
				// create the socket server object
				try {
					serverLogin = new ServerSocket(9877);
					// creating socket and waiting for client connection

					// keep listens indefinitely until receives 'exit' call or program terminates
					while (true) {
						System.out.println("Waiting for the client request");
						Socket socket = serverLogin.accept();
						ClientHandler clientHandler = new ClientHandler(socket, clients);
						Thread t = new Thread(clientHandler);
						clients.add(clientHandler);
						t.start();

						// clientHandler.
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		invitations.start();

		System.out.println("Shutting down Socket server!!");
		// close the ServerSocket object
		// server.close();
	}

}