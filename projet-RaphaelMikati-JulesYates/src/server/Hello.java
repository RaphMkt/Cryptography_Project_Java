package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The class that enables the HELLO request with the server, given an
 * input/output stream and a socket
 * 
 * @author Jules Yates
 * @author Raphael Mikati
 * 
 */
public class Hello {
	/**
	 * The int will contain the int-typed data that will be exchanged with the
	 * server
	 */
	private int intData;
	/**
	 * The error String will store the eventual error the server could send back
	 * to the client
	 */
	private String error;
	/**
	 * The boolean that will contain true if the HELLO request was successful,
	 * or false if it was not.
	 */
	private boolean saidHello;

	/**
	 * Sends a HELLO request to the server, and handles whatever the server send
	 * back to it
	 * 
	 * @param input
	 *            the DataInputStream that has been initialised in the main
	 * @param output
	 *            the DataOutputStream that has been initialised in the main
	 * @param saidHello
	 *            the boolean type that, if true, means the client is connected
	 *            to the server
	 * @throws IOException
	 */
	public boolean sayHello(DataInputStream input, DataOutputStream output) throws IOException {

		intData = 0;
		output.writeInt(intData);

		// receiving HELLO_RESP
		intData = input.readInt();
		// by default the HELLO request has not worked
		saidHello = false;
		switch (intData) {
		case 100:
			saidHello = true;
			System.out.println("Connected to Server\n");
			break;
		// we handle the case where the server sends back an error message
		case 199:
			intData = input.readInt();
			char c;
			error = new String();
			for (int i = 0; i < intData; i++) {
				c = input.readChar();
				error = error + c;
			}
			System.out.println(error);
			input.reset();
			break;

		}
		return saidHello;
	}

}
