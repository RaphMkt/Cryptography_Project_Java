package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The class that enables the BYE request. Once called, it only closes the TCP
 * connection between the client and the server, but that connection can be set
 * back up by requesting HELLO.
 * 
 * @author Jules YATES
 * @author Raphael MIKATI
 *
 */
public class Bye {
	/**
	 * The int intData will handle the int-typed data exchanged between the
	 * client and the server.
	 */
	private int intData;
	/**
	 * The String error will contain the eventual error message sent back by the
	 * server.
	 */
	private String error;

	public void byeBye(DataInputStream input, DataOutputStream output) throws IOException {
		output.writeInt(90);
		System.out.println("BYE");
		intData = input.readInt();
		switch (intData) {
		case 190:
			System.out.println("You have successfully disconnected from the server.\n You can"
					+ "now exit the client (type 6 in the console).\n");
			break;
		// in case the server sends back an error
		case 199:
			intData = input.readInt();
			char c;
			error = new String();
			for (int i = 0; i < intData; i++) {
				c = input.readChar();
				error = error + c;
			}
			System.out.println(error);
			break;
		}
	}

}
