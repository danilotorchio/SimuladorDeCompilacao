package compilador;

import java.util.ArrayList;

public class AnalisadorLexico implements IAnalisadorLexico {

	private final String exp;
	private ArrayList<Character> alfabeto;
	private int pos;

	public AnalisadorLexico(String exp) {
		this.exp = exp;
		this.pos = 0;

		initAlfabeto();
	}

	@Override
	public boolean realizarAnaliseLexica() {
		boolean lexResult = true;
		// for (int i = 0; i < this.exp.length() - 1; i++) {
		// lexResult = getAlfabeto().contains(this.exp.charAt(i));
		// if (!lexResult)
		// break;
		// }
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
	}

	@Override
	public ArrayList<Character> getAlfabeto() {
		return this.alfabeto;
	}

}
