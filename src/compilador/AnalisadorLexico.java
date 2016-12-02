package compilador;

import java.util.ArrayList;
import java.util.function.Consumer;

public class AnalisadorLexico implements IAnalisadorLexico {

	private final String exp;
	private ArrayList<Character> alfabeto;
	private int pos;

	public AnalisadorLexico(String exp) {
		this.exp = exp;
		this.pos = 0;

		initAlfabeto();
	}

	public void iniciar() {
		this.pos = 0;
	}

	@Override
	public int realizarAnaliseLexica(Consumer<String> logger) {
		logger.accept("Iniciando verificação do alfabeto...");
		int lexResult = -1;

		for (int i = 0; i < this.exp.length(); i++) {
			if (!getAlfabeto().contains(this.exp.charAt(i))) {
				lexResult = i;
				break;
			}
		}

		return lexResult;
	}

	@Override
	public int getPosicaoLex() {
		return this.pos;
	}

	@Override
	public char proximoLex() {
		if (!finalDaExpressaoLex()) {
			if (inicioDaExpressaoLex())
				this.pos++;

			return this.exp.charAt(this.pos++);
		} else
			return this.exp.charAt(this.exp.length() - 1);
	}

	@Override
	public char anteriorLex() {
		if (!inicioDaExpressaoLex()) {
			if (finalDaExpressaoLex())
				this.pos--;

			return this.exp.charAt(this.pos--);
		} else {
			return this.exp.charAt(0);
		}
	}

	@Override
	public boolean inicioDaExpressaoLex() {
		return this.pos == -1;
	}

	@Override
	public boolean finalDaExpressaoLex() {
		return this.pos == this.exp.length();
	}

	@Override
	public void initAlfabeto() {
		this.alfabeto = new ArrayList<Character>();

		this.alfabeto.add('+');
		this.alfabeto.add('-');
		this.alfabeto.add('*');
		this.alfabeto.add('/');
		this.alfabeto.add('0');
		this.alfabeto.add('1');
		this.alfabeto.add('2');
		this.alfabeto.add('3');
		this.alfabeto.add('4');
		this.alfabeto.add('5');
		this.alfabeto.add('6');
		this.alfabeto.add('7');
		this.alfabeto.add('8');
		this.alfabeto.add('9');
		this.alfabeto.add('(');
		this.alfabeto.add(')');
	}

	@Override
	public ArrayList<Character> getAlfabeto() {
		return this.alfabeto;
	}

}
