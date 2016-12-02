package compilador;

import java.util.function.Consumer;

public class Compilador implements ICompilador {

	private AnalisadorLexico lex;
	private AnalisadorSintatico sin;

	@Override
	public void iniciarCompilacao(String expressao, Consumer<String> logger) {
		lex = new AnalisadorLexico(expressao);
		sin = new AnalisadorSintatico(lex);

		sin.realizarAnaliseSintatica(logger);
	}

}
