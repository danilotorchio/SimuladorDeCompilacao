package compilador;

import java.util.function.Consumer;

interface ICompilador {

	void iniciarCompilacao(String expressao, Consumer<String> logger);

}
