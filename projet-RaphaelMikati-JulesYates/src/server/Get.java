package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import cipher.Cipher;

import java.io.BufferedReader;

/**
 * The class that enables the GET request with the server. It will extract the
 * text from the stored file, of which you have given the id and write it in the
 * data/fichier_recupere.txt file. The file name will be interactively request
 * within the getFile method.
 * 
 * @author Jules YATES
 * @author Raphael MIKATI
 *
 */
public class Get {
	/**
	 * The int variable that will handle the data types that will be exchanged
	 * between the client and the server
	 */
	public int intData;
	/**
	 * The String variable that will contain the eventual error sent back by the
	 * server
	 */
	public String error;
	/**
	 * The String variable that will contain the text extracted from the file
	 */
	public String fileString;

	/**
	 * This method gets the data from the encrypted file stored in the server,
	 * and writes it in the data/fichier_recupere.txt file. The id of said
	 * stored file will be asked, as for the encryption method.
	 * 
	 * @param input
	 *            The DataInputStream object given in the main class linked with
	 *            the server
	 * @param output
	 *            The DataOutputStream object given in the main class linked
	 *            with the server
	 * @param socket
	 *            The Socket object given in the main class to communicate with
	 *            the server
	 * @param msg
	 *            The BufferedReader object that reads what's inputed with your
	 *            keyboard
	 * @throws IOException
	 */
	public void getFile(DataInputStream input, DataOutputStream output, BufferedReader msg) throws IOException {
		intData = 4;
		// sending the request
		output.writeInt(intData);
		System.out.println("GET");
		// sending the id
		System.out.println("enter the id of the file you want to get back");
		String id = msg.readLine();
		output.writeLong(Long.parseLong(id));
		// receiving the server data
		intData = input.readInt();
		switch (intData) {
		case 104:
			System.out.println("the file with id :" + id + "will be gotten\n");
			intData = input.readInt();
			char c;
			fileString = new String();
			for (int i = 0; i < intData; i++) {
				c = input.readChar();
				fileString += c;
			}

			// decrypting the text
			String decryptString = new String();
			System.out.println(
					"choose your decryption mechanism :\n 1->AtBash\n 2->Caesar\n 3->Keyword\n 4->ROT13\n 5->Vigenere\n");
			String dechiffre = msg.readLine();
			switch (dechiffre) {
			case "1":
				Cipher decrypt = cipher.FabricCipher
						.create(cipher.Code.AtBash);/*
													 * instantiating the
													 * decryption object
													 */
				decryptString = decrypt.Decrypt(fileString);
				break;

			case "2":
				System.out.println("ennter the shift key given to you earlier :\n");
				String text2 = msg.readLine();
				decrypt = cipher.FabricCipher.create(cipher.Code.Caesar, Integer.parseInt(text2));
				decryptString = decrypt.Decrypt(fileString);
				break;

			case "3":
				System.out.println("enter the key given to you earlier :\n");
				text2 = msg.readLine();
				decrypt = cipher.FabricCipher.create(cipher.Code.Keyword, text2);
				decryptString = decrypt.Decrypt(fileString);
				break;

			case "4":
				decrypt = cipher.FabricCipher.create(cipher.Code.ROT13);
				decryptString = decrypt.Decrypt(fileString);
				break;

			case "5":
				System.out.println("enter the key given to you earlier :\n");
				text2 = msg.readLine();
				decrypt = cipher.FabricCipher.create(cipher.Code.Vigenere, text2);
				decryptString = decrypt.Decrypt(fileString);
				break;
			}

			// writing the text in a file

			System.out.println("your text is in the file recovered_data/recovered_file.txt\n");
			FileWriter writer = new FileWriter("recovered_data/recovered_file.txt");
			writer.write(decryptString);
			writer.flush();
			writer.close();
			break;
		// case where the server sends back an error
		case 199:
			intData = input.readInt();
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
