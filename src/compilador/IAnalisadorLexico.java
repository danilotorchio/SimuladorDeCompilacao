package compilador;

import java.util.ArrayList;

interface IAnalisadorLexico {

	void initAlfabeto();

	ArrayList<Character> getAlfabeto();

	boolean realizarAnaliseLexica();

	int getPosicaoLex();

	char proximoLex();

	char anteriorLex();

	boolean inicioDaExpressaoLex();

	boolean finalDaExpressaoLex();

}
