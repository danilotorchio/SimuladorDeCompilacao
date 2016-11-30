package swing;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MainForm extends JFrame {

	private JMenuBar menuBar;
	private JMenu mArquivo, mAjuda;
	private JMenuItem miSair, miSobre;

	public MainForm() throws HeadlessException {
		super("Simulação de Compilação");

		configurarJanela();
		iniciarComponentes();
		configurarAcaoDosMenus();
	}

	private void configurarAcaoDosMenus() {
		miSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Mensagem",
						JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
			}
		});

		miSobre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Simulador de coisas legais!! =)");
			}
		});
	}

	private void configurarJanela() {
		this.setSize(600, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void iniciarComponentes() {
		menuBar = new JMenuBar();

		mArquivo = new JMenu("Arquivo");
		mArquivo.setMnemonic('A');
		menuBar.add(mArquivo);

		miSair = new JMenuItem("Sair");
		miSair.setMnemonic('r');
		mArquivo.add(miSair);

		mAjuda = new JMenu("Ajuda");
		mAjuda.setMnemonic('j');
		menuBar.add(mAjuda);

		miSobre = new JMenuItem("Sobre");
		miSobre.setMnemonic('s');
		mAjuda.add(miSobre);

		this.setJMenuBar(menuBar);
	}

}
