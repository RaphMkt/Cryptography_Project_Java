package cipher;

/**
 * The class implementing the factory
 * 
 * @author : Raphael Mikati
 * @author : Jules Yates
 */

public class FabricCipher {

	/**
	 * One of the 3 methods instantiating a Cipher object
	 * 
	 * @param code
	 *            the code with which the user would like to encrypt or decrypt
	 *            a message
	 * 
	 * @return an instance of the wished code if it is possible with the given
	 *         arguments
	 * 
	 * @exception IllegalArgumentException
	 */
	public static Cipher create(Code code) {
		// Inventory of the 2 possibles encryption methods with such arguments
		Code code_possible1;
		code_possible1 = Code.ROT13;
		Code code_possible2;
		code_possible2 = Code.AtBash;
		/* Checking the given code */
		if (code == code_possible1) {
			Cipher C = new ROT13();
			return C;
		} else if (code == code_possible2) {
			Cipher C = new AtBash();
			return C;
		} else {
			throw new IllegalArgumentException("The given code is either not valid, or requires some other arguments");

		}
	}

	/**
	 * An auxiliary method that will be used to check whether a given String key
	 * is valid or not in the following "create" method
	 * 
	 * @param key
	 *            a String representing the key that will be checked
	 * 
	 * @return true if the key is valid and false otherwise
	 */

	private static boolean is_valid(String key) {
		char key_tab[] = key.toCharArray();
		int len_tab = key_tab.length;
		for (int i = 0; i < len_tab; i++) {
			if (((key_tab[i] <= 90) && (key_tab[i] >= 65)) || ((key_tab[i] >= 97)
					&& (key_tab[i] <= 122))) /*
												 * Checking if the current
												 * character is either a lower
												 * case letter or an upper one,
												 * and not a special character
												 * (ç, à, é, etc...)
												 */ {
				for (int j = 0; j < len_tab; j++) {
					if ((key_tab[i] == key_tab[j])
							&& (i != j))/*
										 * Checking whether or not there are two
										 * same letters in the same key
										 */ {
						return false;
					}
				}
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * Another method to instantiate a Cipher object
	 * 
	 * @param code
	 *            the code with which the user would like to encrypt or decrypt
	 *            a message
	 * 
	 * @return an instance of the wished code if it is possible with the given
	 *         arguments
	 * 
	 * @exception IllegalArgumentException
	 */
	public static Cipher create(Code code, String key) {
		/*
		 * Inventory of the 2 possibles encryption methods with such arguments
		 */
		Code code_possible1;
		code_possible1 = Code.Keyword;
		Code code_possible2;
		code_possible2 = Code.Vigenere;
		/* Checking the given key */
		if (is_valid(key)) {
			/* Checking the given code */
			if (code == code_possible1) {
				Cipher C = new Keyword(key);
				return C;

			} else if (code == code_possible2) {
				Cipher C = new Vigenere(key);
				return C;
			} else {
				throw new IllegalArgumentException(
						"The given code is either not valid, or requires some other arguments");

			}
		} else {
			throw new IllegalArgumentException("The given key is not valid");

		}
	}

	/**
	 * The last method to instantiate a Cipher object
	 * 
	 * @param code
	 *            the code with which the user would like to encrypt or decrypt
	 *            a message
	 * 
	 * @return an instance of the wished code if it is possible with the given
	 *         arguments
	 * 
	 * @exception IllegalArgumentException
	 */
	public static Cipher create(Code code, int shift) {
		/*
		 * Inventory of the 2 possibles encryption methods with such arguments
		 */
		Code code_possible;
		code_possible = Code.Caesar;
		/* Checking the given code */
		if (code == code_possible) {
			Cipher C = new Caesar(shift);
			return C;
		} else {
			throw new IllegalArgumentException("The given code is either not valid, or requires some other arguments");

		}
	}

}
