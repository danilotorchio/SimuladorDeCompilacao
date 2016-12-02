package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import compilador.Compilador;

@SuppressWarnings("serial")
public class MainForm extends JFrame {

	private JMenuBar menuBar;
	private JMenu mArquivo, mAjuda;
	private JMenuItem miReiniciar, miSair, miSobre;

	private JLabel lbExpressao;
	private JTextField tfExpressao;
	private JTextArea taLog;

	private JButton btSimular;

	private Compilador compilador;

	public MainForm() throws HeadlessException {
		super("Compilador");

		configurarJanela();
		iniciarComponentes(this.getContentPane());
		configurarAcaoDosMenus();

		this.pack();
	}

	private void configurarAcaoDosMenus() {
		miReiniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ((compilador != null) && (JOptionPane.showConfirmDialog(null, "Reiniciar o programa?", "Confirmação",
						JOptionPane.YES_NO_OPTION) == 0)) {
					taLog.setText("");
					tfExpressao.setText("");
					tfExpressao.requestFocus();

					compilador = null;
				}
			}
		});

		miSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Confirmação",
						JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
			}
		});

		miSobre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Colocar créditos e propósito do projeto!", "Sobre o Sistema",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	private void configurarJanela() {
		this.setSize(600, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setMenu() {
		menuBar = new JMenuBar();

		mArquivo = new JMenu("Arquivo");
		mArquivo.setMnemonic('A');
		menuBar.add(mArquivo);

		miReiniciar = new JMenuItem("Reiniciar");
		miReiniciar.setMnemonic('e');
		mArquivo.add(miReiniciar);

		mArquivo.addSeparator();

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

	private void iniciarComponentes(final Container container) {
		setMenu();

		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout());
		panelNorth.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new FlowLayout());
		panelSouth.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		lbExpressao = new JLabel("Expressão: ");
		panelNorth.add(lbExpressao);

		tfExpressao = new JTextField(46);
		panelNorth.add(tfExpressao);

		btSimular = new JButton("Iniciar");
		btSimular.addActionListener(new CompiladorHandler());
		panelNorth.add(btSimular);

		taLog = new JTextArea(20, 60);
		taLog.setBackground(Color.BLACK);
		taLog.setForeground(Color.GREEN);
		taLog.setEditable(false);
		panelSouth.add(taLog);

		container.add(panelNorth, BorderLayout.NORTH);
		container.add(panelSouth, BorderLayout.SOUTH);
	}

	public class CompiladorHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			taLog.setText("");

			if (compilador == null) {
				compilador = new Compilador();
			}

			if (tfExpressao.getText().length() > 0) {
				logger("Iniciando processo de compilação...\n");
				compilador.iniciarCompilacao(tfExpressao.getText(), this::logger);
			} else {
				logger("Erro: Nenhuma expressão fornecida...");
			}
		}

		public void logger(String str) {
			String sText = taLog.getText();

			if (sText.length() == 0)
				taLog.setText("-> " + str);
			else
				taLog.setText(sText + "\n" + "-> " + str);
		}
	}

}
