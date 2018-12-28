package cipher;

/**
 * The class that implements the Caesar method
 * 
 * @author : Raphael Mikati
 * @author : Jules Yates
 *
 * 
 */

class Caesar implements Cipher {

	/**
	 * Declaration of the attribute representing the shift in the Caesar method
	 * 
	 */
	private int shift;

	/**
	 * Definition of the constructor
	 *
	 * @param shift
	 *            the int representing the shift that will be used in the Caesar
	 *            method
	 */
	public Caesar(int shift) {
		this.shift = shift;
	}

	/**
	 * The Caesar encryption method
	 * 
	 * @param text
	 *            the String to encrypt
	 * 
	 * @return the text encrypted with the Caesar encryption method
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
					/* The Caesar encryption formula */
					int ascii = 65 + ((tab[i] - 65 + shift) % 26);
					tab[i] = (char) ascii;
				}
				/* Case : Lower case letter */
				else if ((tab[i] <= 122) && (tab[i] >= 97)) {
					/* The Caesar encryption formula */
					int ascii = 97 + ((tab[i] - 97 + shift) % 26);
					tab[i] = (char) ascii;
				}
			}
		}
		text = String.valueOf(tab);
		return text;

	}

	/**
	 * The Caesar decryption method
	 * 
	 * @param cryptext
	 *            the String to decrypt
	 * 
	 * @return the text decrypted with the Caesar decryption method
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
					/* The Caesar decryption formulas */
					int u = (tab[i] - 65 - shift) % 26;
					if (u >= 0) {
						int ascii = 65 + u;
						tab[i] = (char) ascii;
					} else {
						int ascii = 91 + u;
						tab[i] = (char) ascii;
					}
				}
				/* Case : Lower case letter */
				else if ((tab[i] <= 122) && (tab[i] >= 97)) {
					/* The Caesar decryption formulas */
					int u = (tab[i] - 97 - shift) % 26;
					if (u >= 0) {
						int ascii = 97 + u;
						tab[i] = (char) ascii;
					} else {
						int ascii = 123 + u;
						tab[i] = (char) ascii;
					}
				}
			}
		}
		cryptext = String.valueOf(tab);
		return cryptext;
	}
}
