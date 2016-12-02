package principal;

import javax.swing.SwingUtilities;

import swing.MainForm;

public class Simulador {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				runApplication();
			}
		});
	}

	private static void runApplication() {
		MainForm form = new MainForm();
		form.setVisible(true);
	}

}
