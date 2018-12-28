package cipher;

/**
 * The class that implements the Keyword method
 * 
 * @author : Raphael Mikati
 * @author : Jules Yates
 * 
 * 
 */

class Keyword implements Cipher {

	/**
	 * Declaration of the attribute representing the key that will be used in
	 * the Keyword method
	 * 
	 */
	private String key;

	/**
	 * Definition of the constructor
	 *
	 * @param key
	 *            the String representing the key that will be used in the
	 *            Keyword method
	 */
	public Keyword(String key) {
		this.key = key;
	}

	/** Definition of 3 auxiliary methods */

	/**
	 * nbr_similar counts the number of identical letters in the key and in the
	 * alphabet until a given letter i.e it counts the number of letters of the
	 * alphabet until a given one already used in the key (that thus cannot be
	 * reused)
	 * 
	 * @param start_ascii
	 *            the int representing the first letter of the alphabet in the
	 *            ascii system (65 for A, 97 for a)
	 * 
	 * @param stop_letter
	 *            the int representing the letter until which the method
	 *            compares the letters already used in the key
	 * 
	 * @param key_tab
	 *            the array of characters representing the key
	 * 
	 * @return an int corresponding to the number of identical letters in the
	 *         key and in the alphabet until stop_letter
	 */
	private int nbr_similar(int start_ascii, int stop_letter, char[] key_tab) {
		int len_key = key_tab.length;
		int counter = 0;
		for (int k = start_ascii; k < stop_letter; k++) {
			for (int j = 0; j < len_key; j++) {
				if (k == key_tab[j]) {
					counter = counter + 1;
				}
			}
		}
		return counter;
	}

	/**
	 * Checks if a given letter belongs to the key
	 * 
	 * @param letter
	 *            the int representing the letter (ascii system) which ought to
	 *            be checked
	 * 
	 * @param key_tab
	 *            the array of characters representing the key
	 * 
	 * @return a boolean whose value is true if the given letter belongs to the
	 *         key, false otherwise
	 */
	private boolean belong(int letter, char key_tab[]) {
		int len_key = key_tab.length;
		for (int i = 0; i < len_key; i++) {
			int key_tab_i = (int) key_tab[i];
			if (key_tab_i == letter) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gives the position of a given letter in the key
	 * 
	 * @param letter
	 *            the int representing the letter (ascii system) whose position
	 *            is to be determined
	 * 
	 * @param key_tab
	 *            the array of characters representing the key
	 * 
	 * @return an int corresponding to the position of the given letter if it
	 *         belongs to the key
	 */
	private int get_index(int letter, char[] key_tab) {
		int len_key = key_tab.length;
		int index = -1;
		if (belong(letter, key_tab)) {
			for (int i = 0; i < len_key; i++) {
				if (letter == key_tab[i]) {
					index = i;
				}
			}
		}
		return index;
	}

	/**
	 * The Keyword encryption method
	 * 
	 * @param text
	 *            the String to encrypt
	 * 
	 * @return the text encrypted with the Keyword encryption method
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
		for (int i = 0; i < len_tab; i++) {
			if (tab[i] != ' ') {
				/* Case : Upper case letter */
				if ((tab[i] <= 90) && (tab[i] >= 65)) {
					/*
					 * Checking if the current letter has to be treated with a
					 * letter of the key
					 */
					if ((tab[i] - 65) < len_key) {
						int tab_i = (int) tab[i];
						int ascii = key_upper_tab[tab_i - 65];
						tab[i] = (char) ascii;
					} else {
						int tab_i = (int) tab[i]; /*
													 * Conversion of tab[i] into
													 * an int in order to use it
													 * in our auxiliary method
													 */
						/*
						 * The Keyword encryption formula if the letter has to
						 * be treated with a letter not included in the key
						 */
						int ascii = tab[i] - len_key + nbr_similar(65, tab_i, key_upper_tab);
						tab[i] = (char) ascii;
					}
				}
				/* Case : Lower case letter */
				else if ((tab[i] <= 122) && (tab[i] >= 97)) {
					/*
					 * Checking if the current letter has to be treated with a
					 * letter of the key
					 */
					if ((tab[i] - 97) < len_key) {
						int tab_i = (int) tab[i];
						int ascii = key_lower_tab[tab_i - 97];
						tab[i] = (char) ascii;
					} else {
						int tab_i = (int) tab[i]; /*
													 * Conversion of tab[i] into
													 * an int in order to use it
													 * in our auxiliary method
													 */
						/*
						 * The Keyword encryption formula if the letter has to
						 * be treated with a letter not included in the key
						 */
						int ascii = tab[i] - len_key + nbr_similar(97, tab_i, key_lower_tab);
						tab[i] = (char) ascii;
					}
				}
			}
		}
		text = String.valueOf(tab);
		return text;
	}

	/**
	 * The Keyword decryption method
	 * 
	 * @param cryptext
	 *            the String to decrypt
	 * 
	 * @return the text decrypted with the Keyword decryption method
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
		for (int i = 0; i < len_tab; i++) {
			if (tab[i] != ' ') {
				/* Case : Upper case letter */
				if ((tab[i] <= 90) && (tab[i] >= 65)) {
					int tab_i = (int) tab[i]; /*
												 * Conversion of tab[i] into an
												 * int in order to use it in our
												 * auxiliary method
												 */
					/*
					 * Checking if the current letter corresponds to a letter of
					 * the key
					 */
					if (belong(tab_i, key_upper_tab)) {
						int ascii = 65 + get_index(tab_i, key_upper_tab);
						tab[i] = (char) ascii;
					}
					/*
					 * The Keyword decryption formula if the letter does not
					 * correspond to a letter not included in the key
					 */
					else {
						int ascii = tab[i] + len_key - nbr_similar(65, tab_i, key_upper_tab);
						tab[i] = (char) ascii;
					}
				}
				/* Case : Lower case letter */
				else if ((tab[i] <= 122) && (tab[i] >= 97)) {
					int tab_i = (int) tab[i]; /*
												 * Conversion of tab[i] into an
												 * int in order to use it in our
												 * auxiliary method
												 */
					/*
					 * Checking if the current letter corresponds to a letter of
					 * the key
					 */
					if (belong(tab_i, key_lower_tab)) {
						int ascii = 97 + get_index(tab_i, key_lower_tab);
						tab[i] = (char) ascii;
					}
					/*
					 * The Keyword decryption formula if the letter does not
					 * correspond to a letter not included in the key
					 */
					else {
						int ascii = tab[i] + len_key - nbr_similar(97, tab_i, key_lower_tab);
						tab[i] = (char) ascii;
					}
				}
			}
		}
		cryptext = String.valueOf(tab);
		return cryptext;
	}
}
