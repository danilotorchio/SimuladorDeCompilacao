package compilador;

import java.util.function.Consumer;

public class Compilador implements ICompilador {

	@Override
	public void iniciarCompilacao(String expressao, Consumer<String> logger) {
		AnalisadorLexico lex = new AnalisadorLexico(expressao);

		if (lex.realizarAnaliseLexica()) {
			while (!lex.finalDaExpressaoLex()) {
				logger.accept("Lex: " + lex.proximoLex());
			}
			logger.accept("Lex: " + lex.proximoLex());
			while (!lex.inicioDaExpressaoLex()) {
				logger.accept("Lex: " + lex.anteriorLex());
			}
			logger.accept("Lex: " + lex.anteriorLex());
		}
	}

	@Override
	public void limparInstancias() {
		// TODO
	}

}
