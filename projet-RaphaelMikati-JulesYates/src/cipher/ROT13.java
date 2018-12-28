package cipher;

/**
 * The class that implements the ROT13 method
 * 
 * @author : Raphael Mikati
 * @author : Jules Yates
 *
 * 
 */

class ROT13 implements Cipher {

	/**
	 * The ROT13 encryption method
	 * 
	 * @param text
	 *            the String to encrypt
	 * 
	 * @return the text encrypted with the ROT13 encryption method
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
					int ascii = 65 + ((tab[i] - 52) % 26);
					tab[i] = (char) ascii;
				}
				/* Case : Lower case letter */
				else if ((tab[i] <= 122) && (tab[i] >= 97)) {
					/* The encryption formula for lower case letters */
					int ascii = 97 + ((tab[i] - 84) % 26);
					tab[i] = (char) ascii;
				}
			}
		}
		text = String.valueOf(tab);
		return text;

	}

	/**
	 * The ROT13 decryption method
	 * 
	 * @param cryptext
	 *            the String to decrypt
	 * 
	 * @return the text decrypted with the ROT13 decryption method
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
					/*
					 * Implementing the Caesar decryption formulas with a shift
					 * equals to 13
					 */
					int u = (tab[i] - 78) % 26;
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
					/*
					 * Implementing the Caesar decryption formulas with a shift
					 * equals to 13
					 */
					int u = (tab[i] - 110) % 26;
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
