package compilador;

import java.util.ArrayList;
import java.util.function.Consumer;

interface IAnalisadorLexico {

	void initAlfabeto();

	ArrayList<Character> getAlfabeto();

	int realizarAnaliseLexica(Consumer<String> logger);

	int getPosicaoLex();

	char proximoLex();

	char anteriorLex();

	boolean inicioDaExpressaoLex();

	boolean finalDaExpressaoLex();

}
