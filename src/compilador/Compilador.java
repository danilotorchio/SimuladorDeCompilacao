package compilador;

import java.util.function.Consumer;

public class Compilador implements ICompilador {

	private AnalisadorLexico lex;
	private AnalisadorSintatico sin;

	@Override
	public void iniciarCompilacao(String expressao, Consumer<String> logger) {
		lex = new AnalisadorLexico(expressao);
		lex.realizarAnaliseLexica(logger);

		if (!lex.lexicoAceito) {
			logger.accept("Processo de compilação finalizado com erro de analise lexica...");
			return;
		}

		sin = new AnalisadorSintatico(lex);
		sin.realizarAnaliseSintatica(logger);

		if (!sin.sintaticaAceita) {
			logger.accept("Processo de compilação finalizado com erro de analise sintatica...");
			return;
		}

		logger.accept("Processo de compilação finalizado com sucesso!");
	}

}
