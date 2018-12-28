package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class that handles the LIST request with the server, given an input/output
 * stream, and a socket
 * 
 * @author Jules YATES
 * @author Raphael MIKATI
 * 
 * 
 */

public class List {

	/**
	 * The int data handles the exchanged int types with the server
	 */
	private int intData;
	/**
	 * The error String will contain the eventual error sent back by the server
	 */
	private String error;
	/**
	 * The long type handles the id's sent by the server
	 */
	private Long longData;

	/**
	 * Sends a LIST request to the server, and handles : the initial awnser from
	 * the server, the id's that are sent back by the server (referencing the
	 * stored files), and also writes these id's in a file : data/ids.txt
	 * 
	 * @param input
	 *            the DataInputStream that has been initialized in the main
	 * @param output
	 *            the DataOutputStream that has been initialized in the main
	 * @throws IOException
	 */
	public void getList(DataInputStream input, DataOutputStream output) throws IOException {
		intData = 1;
		output.writeInt(intData);

		// receiving LIST_RESP
		intData = input.readInt();

		switch (intData) {
		case 101:
			System.out.println("La liste va etre envoyée par le serveur\n");
			// the number of saved files (= number of ids)
			intData = input.readInt();
			System.out.println("there are " + intData + " files in storage");

			// writing the ids in data/ids.txt
			System.out.println("les id ont été écrites dans le fichier id_data/ids.txt\n");
			FileWriter writer = new FileWriter("id_data/ids.txt");

			for (int i = 0; i < intData; i++) {
				longData = input.readLong();
				System.out.println(longData + ", ");
				writer.write(String.valueOf(longData) + "\n");
			}

			writer.flush();
			writer.close();
			break;
		// handles the case where the server sends back an error
		case 199:
			intData = input.readInt();
			char c=0;
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
