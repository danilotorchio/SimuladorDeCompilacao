package compilador;

import java.util.function.Consumer;

interface IAnalisadorSintatico {

	boolean realizarAnaliseSintatica(Consumer<String> logger);

	Character reduzirExpressao(String exp);

	Character reduzirParenteses(Consumer<String> logger);

}
