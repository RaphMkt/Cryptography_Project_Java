package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.ConnectException;

/**
 * This executable client interacts with the storage server. The client is
 * interactive, meaning that the user only has to execute it, then all
 * possibilities can be easily accessed from within the command line window.
 * 
 * The basic errors, like sending a request before having sent HELLO, are
 * handled in this client, rather than relying on server for management, since
 * having several errors done in a row can possibly cause the server to
 * mismanage further requests.
 * 
 * @author Jules YATES
 * @author Raphael MIKATI
 * 
 *
 */

public class CipherTCPClientMain {
	/**
	 * 
	 * @param args
	 *            No arguments are required for this client
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {

		System.out.println("The client app for the CS Project");

		/*
		 * creating the communication socket with the server
		 */
		String serverAddress = "localhost";
		int serverPort = 6789;

		try {
			Socket socket = new Socket(serverAddress, serverPort);

			/*
			 * Initializing DataInputStream & DataOutputStream Needed to create
			 * data streams with the server
			 */
			DataInputStream input = new DataInputStream(socket.getInputStream());
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());
			BufferedReader msg = new BufferedReader(new InputStreamReader(System.in));

			/*
			 * Infinite loop that enables the user to interact with the server
			 */
			boolean requesting = true;
			boolean saidHello = false; // to know if the user has said "Hello"
										// to the server or not
			while (requesting) {
				System.out.println(
						"What is your request?\n 0 for HELLO\n 1 for LIST\n 2 for ADD\n 3 for REMOVE\n 4 for GET\n 5 for BYE\n 6 to leave client\n");
				String str = msg.readLine();

				switch (str) {

				/**
				 * do you want to connect to the server? Sending HELLO
				 */
				case "0":
					Hello sayingHello = new Hello();
					saidHello = sayingHello.sayHello(input, output);
					break;

				/**
				 * Do you wish to terminate the connection?
				 */

				/**
				 * Sending LIST request
				 */
				case "1":

					if (saidHello == true) {
						List idList = new List();
						idList.getList(input, output);
					} else {
						System.out.println("Please start by sending the HELLO request (type 0 in the console).\n");
					}
					break;

				/**
				 * sending ADD request
				 */
				case "2":

					if (saidHello == true) {
						Add add = new Add();
						add.addFile(input, output, msg);
					} else {
						System.out.println("Please start by sending the HELLO request (type 0 in the console).\n");
					}

					break;

				/**
				 * sending REMOVE request
				 */
				case "3":
					if (saidHello == true) {
						Remove remove = new Remove();
						remove.removeFile(input, output, msg);
					} else {
						System.out.println("Please start by sending the HELLO request (type 0 in the console).\n");
					}

					break;

				/**
				 * sending GET request
				 */
				case "4":
					if (saidHello == true) {
						Get get = new Get();
						get.getFile(input, output, msg);
					} else {
						System.out.println("Please start by sending the HELLO request (type 0 in the console).\n");
					}
					break;

				/**
				 * sending BYE request
				 */
				case "5":
					if (saidHello == true) {
						Bye goodbye = new Bye();
						goodbye.byeBye(input, output);
					} else {
						System.out.println("Please start by sending the HELLO request(type 0 in the console).\n");
					}
					break;

				/**
				 * leaving client
				 */
				case "6":
					if (saidHello == true) {
						requesting = false;
					} else {
						System.out.println("Please start by sending the HELLO request(type 0 in the console).\n");
					}
					break;

				}

			}
			socket.close();
			System.out.println("You have successfully left the client program. Goodbye!\n");
		} catch (ConnectException e) {
			System.out.println("Please turn on the server before using the client. Close the client and start again,\n"
					+ "after having turned on the server.\n");
		}
	}
}
