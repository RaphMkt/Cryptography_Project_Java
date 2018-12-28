package cipher;

/**
 * The class that implements the Vigenere method
 * 
 * @author : Raphael Mikati
 * @author : Jules Yates
 */

class Vigenere implements Cipher {

	/**
	 * Declaration of the attribute representing the key that will be used in
	 * the Vigenere method
	 */
	private String key;

	/**
	 * Definition of the constructor
	 *
	 * @param key
	 *            the String representing the key that will be used in the
	 *            Vigenere method
	 */
	public Vigenere(String key) {
		this.key = key;
	}

	/**
	 * The Vigenere encryption method
	 * 
	 * @param text
	 *            the String to encrypt
	 * 
	 * @return the text encrypted with the Vigenere encryption method
	 */
	public String Encrypt(String text) {
		/*
		 * Conversion of a String into an array of characters (side effect : the
		 * argument is modified)
		 */
		char tab[] = text.toCharArray();
		int len_tab = tab.length;
		/*
		 * Creation of a key for upper case letters and another one for lower
		 * case ones. Then, extraction of their length
		 */
		String key_upper = key.toUpperCase();
		char key_upper_tab[] = key_upper.toCharArray();
		String key_lower = key.toLowerCase();
		char key_lower_tab[] = key_lower.toCharArray();
		int len_key = key_lower_tab.length;
		/* Heart of the programme */
		int index_not_whitespace = 0; /*
										 * Incremental counter that counts the
										 * indexes of the characters that are
										 * not a whitespace
										 */
		int j = 0; /*
					 * index of the position currently in use in the key, its
					 * value is index_not_whitespace modulo the length of the
					 * key
					 */
		for (int i = 0; i < len_tab; i++) {
			if (tab[i] != ' ') {
				j = index_not_whitespace % len_key;
				index_not_whitespace = index_not_whitespace + 1;
				/* Case : Upper case letter */
				if ((tab[i] <= 90) && (tab[i] >= 65)) {
					/* The Vigenere encryption formula */
					int ascii = 65 + ((key_upper_tab[j] + tab[i] - 130) % 26);
					tab[i] = (char) ascii;
				}
				/* Case : Lower case letter */
				else if ((tab[i] <= 122) && (tab[i] >= 97)) {
					/* The Vigenere encryption formula */
					int ascii = 97 + ((key_lower_tab[j] + tab[i] - 194) % 26);
					tab[i] = (char) ascii;
				}
			}

		}
		text = String.valueOf(tab);
		return text;
	}

	/**
	 * The Vigenere decryption method
	 * 
	 * @param cryptext
	 *            the String to decrypt
	 * 
	 * @return the text decrypted with the Vigenere decryption method
	 */
	public String Decrypt(String cryptext) {
		/*
		 * Conversion of a String into an array of characters (side effect : the
		 * argument is modified)
		 */
		char tab[] = cryptext.toCharArray();
		int len_tab = tab.length;
		/*
		 * Creation of a key for upper case letters and another one for lower
		 * case ones. Then, extraction of their length
		 */
		String key_upper = key.toUpperCase();
		char key_upper_tab[] = key_upper.toCharArray();
		String key_lower = key.toLowerCase();
		char key_lower_tab[] = key_lower.toCharArray();
		int len_key = key_lower_tab.length;
		/* Heart of the programme */
		int index_not_whitespace = 0; /*
										 * Incremental counter that counts the
										 * indexes of the characters that are
										 * not a whitespace
										 */
		int j = 0; /*
					 * index of the position currently in use in the key, its
					 * value is index_not_whitespace modulo the length of the
					 * key
					 */
		for (int i = 0; i < len_tab; i++) {
			if (tab[i] != ' ') {
				j = index_not_whitespace % len_key;
				index_not_whitespace = index_not_whitespace + 1;
				/* Case : Upper case letter */
				if ((tab[i] <= 90) && (tab[i] >= 65)) {
					if (key_upper_tab[j] > tab[i]) {
						/*
						 * The Vigenere decryption formula if the corresponding
						 * letter in the key is greater than the encrypted
						 * letter
						 */
						int ascii = 91 - key_upper_tab[j] + tab[i];
						tab[i] = (char) ascii;
					}
					/*
					 * The Vigenere decryption formula if the encrypted letter
					 * is greater than the corresponding letter in the key
					 */
					else {
						int ascii = 65 - key_upper_tab[j] + tab[i];
						tab[i] = (char) ascii;
					}
				} /* Case : Lower case letter */
				else if ((tab[i] <= 122) && (tab[i] >= 97)) {
					/*
					 * The Vigenere decryption formula if the corresponding
					 * letter in the key is greater than the encrypted letter
					 */
					if (key_lower_tab[j] > tab[i]) {
						int ascii = 123 - key_lower_tab[j] + tab[i];
						tab[i] = (char) ascii;
					}
					/*
					 * The Vigenere decryption formula if the encrypted letter
					 * is greater than the corresponding letter in the key
					 */
					else {
						int ascii = 97 - key_lower_tab[j] + tab[i];
						tab[i] = (char) ascii;
					}
				}
			}
		}
		cryptext = String.valueOf(tab);
		return cryptext;
	}

}
