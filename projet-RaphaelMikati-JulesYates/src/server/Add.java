package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;

import cipher.Cipher;

import java.io.BufferedReader;

/**
 * The class that enables the ADD request with the server. It will read and
 * convert the text from a txt file stored in data folder, and send it to the
 * server. Afterwards it handles the anwser, and obtains the id of the newly
 * stored file
 * 
 * @author Jules YATES
 * @author Raphael MIKATI
 *
 */
public class Add {
	/**
	 * the int intData will contain the exchanged int-typed data with the server
	 */
	private int intData;
	/**
	 * The String fileString will contain the text extracted from the users file
	 */
	private String fileString;
	/**
	 * The String error will contain the eventual error message sent back from
	 * the server after a request
	 */
	private String error;
	/**
	 * The Long longData will handle the id sent back by the server
	 */
	private Long longData;

	/**
	 * 
	 * @param input
	 *            the DataInputStream object used in the client
	 * @param output
	 *            the DataOutputStream object used in the client
	 * @param socket
	 *            the Socket object used in the client to connect with the
	 *            servere
	 * @param msg
	 *            the BufferedReader object used to read what is typed on the
	 *            keyboard
	 * @throws IOException
	 * 
	 */
	public void addFile(DataInputStream input, DataOutputStream output, BufferedReader msg) throws IOException {
		try {

			System.out.println("ADD");

			// extracting the text from the "name" file into a reader
			System.out.println(
					"enter the name of the file you want to convert and store.\n That file has to be in the data folder of this project.\n");
			String name = msg.readLine();
			FileReader in = new FileReader("toSend_data/" + name);
			BufferedReader bin = new BufferedReader(in);
			// converting the reader into a String
			fileString = bin.readLine();
			bin.close();
			fileString = fileString.trim();
			// creating a String variable for the encrypted text
			String cryptString = new String();
			// choosing the encryption mechanism
			System.out.println(
					"What encryption mechanism do you want to apply to your text?\n 1->AtBash\n 2->Caesar\n 3->Keyword\n 4->ROT13\n 5->Vigenere\n");
			String chiffre = msg.readLine();
			System.out.println("Don't forget to remember your keys!\n");
			switch (chiffre) {
			case "1":
				Cipher crypt = cipher.FabricCipher
						.create(cipher.Code.AtBash);/*
													 * Instantiating the
													 * encryption object
													 */
				cryptString = crypt.Encrypt(fileString);
				break;

			case "2":
				System.out.println("enter the shift key :\n");
				String text = msg.readLine();
				crypt = cipher.FabricCipher.create(cipher.Code.Caesar, Integer.parseInt(text));
				cryptString = crypt.Encrypt(fileString);
				break;

			case "3":
				System.out.println("enter the key (All letters in the key must be different) :\n");
				text = msg.readLine();
				crypt = cipher.FabricCipher.create(cipher.Code.Keyword, text);
				cryptString = crypt.Encrypt(fileString);
				break;

			case "4":
				crypt = cipher.FabricCipher.create(cipher.Code.ROT13);
				cryptString = crypt.Encrypt(fileString);
				break;

			case "5":
				System.out.println("enter the key (All letters in the key must be different) :\n");
				text = msg.readLine();
				crypt = cipher.FabricCipher.create(cipher.Code.Vigenere, text);
				cryptString = crypt.Encrypt(fileString);
				break;
			}

			intData = 2;
			output.writeInt(intData);
			// sending the number of characters of the String
			intData = cryptString.length();
			output.writeInt(intData);
			// converting the String to a char array (easier for sending the
			// chars)
			char[] charArray = cryptString.toCharArray();
			// sending the chars one by one
			for (int i = 0; i < intData; i++) {
				output.writeChar(charArray[i]);
			}
			// receiving and reading the server response
			intData = input.readInt();
			switch (intData) {
			case 102:
				System.out.println("File added!\n");
				longData = input.readLong();
				System.out.println("new-id : " + longData);
				break;
			// case where the server sends back an error
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
		} catch (Exception e) {
			String message = e.getMessage().trim();
			System.out.println(message);
		}

	}

}
