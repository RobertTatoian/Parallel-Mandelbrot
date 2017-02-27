package main;

import GUI.UserInterface;

/**
 * The starting class of the program, from here the main method calls the constructor to generate the user interface.
 * @author Robert Tatoian
 * @since 02/09/2017'
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new UserInterface();
			}
		});

	}


}
