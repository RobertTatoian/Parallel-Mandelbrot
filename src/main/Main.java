package main;

import GUI.UserInterface;

public class Main {

	public static void main(String[] args) {

//		UserInterface gui = new UserInterface();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new UserInterface();
			}
		});
	}
}
