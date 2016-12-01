package compilador;

import java.util.function.Consumer;

public class Compilador implements ICompilador {

	@Override
	public void iniciarCompilacao(String expressao, Consumer<String> logger) {
		logger.accept("Expressão: " + expressao);
	}

	@Override
	public void limparInstancias() {
		// TODO
	}

}
