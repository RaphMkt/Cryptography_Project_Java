package cipher;

/**
 * The class that implements the AtBash method
 * 
 * @author : Raphael Mikati
 * @author : Jules Yates
 */

class AtBash implements Cipher {

	/**
	 * The AtBash encryption method
	 * 
	 * @param text
	 *            the String to encrypt
	 * 
	 * @return the text encrypted with the AtBash encryption method
	 */
	public String Encrypt(String text) {
		/*
		 * Conversion of a String into an array of characters (side effect : the
		 * argument is modified)
		 */
		char tab[] = text.toCharArray();
		/* Heart of the programme */
		int len_tab = tab.length;
		for (int i = 0; i < len_tab; i++) {
			if (tab[i] != ' ') {
				/* Case : Upper case letter */
				if ((tab[i] <= 90) && (tab[i] >= 65)) {
					/* The encryption formula for upper case letters */
					int ascii = 155 - tab[i];
					tab[i] = (char) ascii;
				}
				/* Case : Lower case letter */
				else if ((tab[i] <= 122) && (tab[i] >= 97)) {
					/* The encryption formula for lower case letters */
					int ascii = 219 - tab[i];
					tab[i] = (char) ascii;
				}
			}
		}
		text = String.valueOf(tab);
		return text;

	}

	/**
	 * The AtBash decryption method
	 * 
	 * @param cryptext
	 *            the String to decrypt
	 * 
	 * @return the text decrypted with the AtBash decryption method
	 */
	public String Decrypt(String cryptext) {
		/*
		 * Conversion of a String into an array of characters (side effect : the
		 * argument is modified)
		 */
		char tab[] = cryptext.toCharArray();
		/* Heart of the programme */
		int len_tab = tab.length;
		for (int i = 0; i < len_tab; i++) {
			if (tab[i] != ' ') {
				/* Case : Upper case letter */
				if ((tab[i] <= 90) && (tab[i] >= 65)) {
					/* The decryption formula for upper case letters */
					int ascii = 155 - tab[i];
					tab[i] = (char) ascii;
				}
				/* Case : Lower case letter */
				else if ((tab[i] <= 122) && (tab[i] >= 97)) {
					/* The decryption formula for lower case letters */
					int ascii = 219 - tab[i];
					tab[i] = (char) ascii;
				}
			}
		}
		cryptext = String.valueOf(tab);
		return cryptext;
	}
}
