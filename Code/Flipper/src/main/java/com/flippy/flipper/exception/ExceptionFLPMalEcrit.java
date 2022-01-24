package com.flippy.flipper.exception;

/**
 * Class that create an Exception of ReaderFlp
 */
public class ExceptionFLPMalEcrit extends Exception {

	/**
	 * Constructor
	 */
	public ExceptionFLPMalEcrit(String s, int ligne) {
		System.out.println("Erreur d'ecriture ligne " + ligne + " dans le fichier " + s);
	}
}
