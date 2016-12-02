package compilador;

import java.util.Random;
import java.util.Stack;
import java.util.function.Consumer;

public class AnalisadorSintatico implements IAnalisadorSintatico {
	private AnalisadorLexico lex;
	public boolean sintaticaAceita;

	public AnalisadorSintatico(AnalisadorLexico lexico) {
		this.lex = lexico;
		this.sintaticaAceita = true;
	}

	@Override
	public boolean realizarAnaliseSintatica(Consumer<String> logger) {
		logger.accept("Iniciando verificação sintatica...");
		logger.accept("Expressao: " + lex.getExpressao());

		Stack<Character> stack = new Stack<Character>();
		Character ch = 0;

		lex.iniciar();
		while (!lex.finalDaExpressaoLex()) {
			ch = lex.proximoLex();

			if (ch == '(') {
				stack.push(reduzirParenteses(logger));
			} else {
				stack.push(ch);
			}
		}

		String str = "";
		for (Character c : stack) {
			str += c;
		}

		if (str.length() > 1) {
			logger.accept("Reduzindo expressão: " + str);
			ch = reduzirExpressao(str);
			logger.accept("Expressão reduzida: " + ch);
		} else {
			ch = str.toCharArray()[0];
		}

		logger.accept("Expressao final: " + ch);
		return true;
	}

	@Override
	public Character reduzirParenteses(Consumer<String> logger) {
		Stack<Character> stack = new Stack<Character>();
		Character ch = 0;

		while (!lex.finalDaExpressaoLex()) {
			ch = lex.proximoLex();

			if (ch == '(') {
				ch = reduzirParenteses(logger);

				if (this.sintaticaAceita) {
					stack.push(ch);
				} else {
					break;
				}
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

		logger.accept("Reduzindo parenteses...");
		logger.accept("Reduzindo expressão: " + str);
		ch = reduzirExpressao(str);
		logger.accept("Expressao reduzida: " + ch);

		return ch;
	}

	@Override
	public Character reduzirExpressao(String exp) {
		boolean b1, b2 = true;

		for (Character chr : exp.toCharArray()) {
			b1 = b2;
			b2 = Character.isDigit(chr) || Character.isLetter(chr);

			if (!b1 && !b2) {
				this.sintaticaAceita = false;
				return chr;
			}
		}

		Random ra = new Random();
		return (char) (65 + ra.nextInt(90 - 65));
	}
}
