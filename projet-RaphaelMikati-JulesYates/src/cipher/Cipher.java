package cipher;

/**
 * The Cipher interface
 * 
 * @author : Raphael Mikati
 * @author : Jules Yates
 */

public interface Cipher {
	/**
	 * The method to encrypt a given text
	 * 
	 * @param text
	 *            the String to encrypt
	 * 
	 * @return the text encrypted with a specific encryption method
	 */
	public String Encrypt(String text);

	/**
	 * The method to decrypt a given encrypted text
	 * 
	 * @param cryptext
	 *            the String to decrypt
	 * 
	 * @return the text decrypted with a specific decryption method
	 */
	public String Decrypt(String cryptext);
}
