package compilador;

import java.util.Random;
import java.util.Stack;
import java.util.function.Consumer;

public class AnalisadorSintatico implements IAnalisadorSintatico {
	private AnalisadorLexico lex;

	public AnalisadorSintatico(AnalisadorLexico lexico) {
		this.lex = lexico;
	}

	@Override
	public boolean realizarAnaliseSintatica(Consumer<String> logger) {
		Stack<Character> stack = new Stack<Character>();
		Character ch = 0;

		lex.iniciar();
		while (!lex.finalDaExpressaoLex()) {
			ch = lex.proximoLex();

			if (ch == '(') {
				stack.push(reduzirParenteses());
			} else {
				stack.push(ch);
			}
		}

		logger.accept(stack.toString());
		return true;
	}

	public Character reduzirParenteses() {
		Stack<Character> stack = new Stack<Character>();
		Character ch = 0;

		while (!lex.finalDaExpressaoLex()) {
			ch = lex.proximoLex();

			if (ch == '(') {
				stack.push(reduzirParenteses());
			} else if (ch == ')') {
				break;
			} else {
				stack.push(ch);
			}
		}

		String str = "";
		for (Character c : stack) {
			str += c;
		}

		return reduzirExpressao(str);
		// TODO Adicionar verificação para quando não consegue reduzir a
		// expressao (char < 65)
	}

	@Override
	public Character reduzirExpressao(String exp) {
		boolean b1, b2 = true;

		for (Character chr : exp.toCharArray()) {
			b1 = b2;
			b2 = Character.isDigit(chr) || Character.isLetter(chr);

			if (!b1 && !b2) {
				return chr;
			}
		}

		Random ra = new Random();
		return (char) (65 + ra.nextInt(90 - 65));
	}
}
