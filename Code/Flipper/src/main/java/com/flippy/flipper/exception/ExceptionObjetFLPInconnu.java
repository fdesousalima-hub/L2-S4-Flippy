package com.flippy.flipper.exception;

import com.flippy.moteur.item.Item;

/**
 * Class that create an Exception of ReaderFlp
 */
public class ExceptionObjetFLPInconnu extends Exception {
	/**
	 * Constructor
	 */
	public ExceptionObjetFLPInconnu(Item i) {
		System.out
				.println("L'objet de type " + i.getClass().getName() + " ne peut pas etre ecris dans un fichier .flp");
	}
}
