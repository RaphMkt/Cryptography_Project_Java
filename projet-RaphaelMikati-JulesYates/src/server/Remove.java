package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.BufferedReader;

/**
 * The class that enables the REMOVE request with the server. It handles the
 * request and the awnser to remove a file for server storage.
 * 
 * @author Jules YATES
 * @author Raphael MIKATI
 *
 */
public class Remove {
	/**
	 * the int intData will contain the exchanged int-typed data with the server
	 */
	private int intData;
	/**
	 * The String error will handle the eventual error message sent back by the
	 * server after a request
	 */
	private String error;

	/**
	 * 
	 * @param input
	 * @param output
	 * @param socket
	 * @param msg
	 * @throws IOException
	 */
	public void removeFile(DataInputStream input, DataOutputStream output, BufferedReader msg) throws IOException {

		intData = 3;
		output.writeInt(intData);
		System.out.println("REMOVE");
		System.out.println("please, enter the id of the file to be deleted");
		String id = msg.readLine();
		output.writeLong(Long.parseLong(id));
		// reception before the server's reply
		intData = input.readInt();
		switch (intData) {
		case 103:
			System.out.println("The file id : " + id + " has been deleted\n");
			break;
		// Case : the server sends an error message
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
