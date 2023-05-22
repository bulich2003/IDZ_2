package org.example;

public class FormulaChecking {
    private char symbol;

//    private int i = -2;
    private int position;
    private String formula;
    private String result;

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String SIGNS = "+-*/";

    public FormulaChecking(String formula) {

        this.formula = formula;
        this.formula += '#';
        position = -1;

        try { result = Formula(); }
        catch (IsNotRightFormula ex) { result = String.valueOf(ex); }
    }

    private String Formula() throws IsNotRightFormula {
        Expression_0();
        if (symbol == '#') return "Формула удовлетворяет критериям";
        else throw new IsNotRightFormula(
            new Exception().getStackTrace()[0].getMethodName(),
            symbol, position, "'#'");
    }

    private void Expression_0() throws IsNotRightFormula {
        readNextSymbol();
        if (IsLetter(symbol)) Expression_2();
        else if (symbol == '[') SquareExpression_0();
        else if (symbol == '(') RoundExpression_0();
        else throw new IsNotRightFormula(
            new Exception().getStackTrace()[0].getMethodName(),
            symbol, position, "'[', '(', 'a', 'b', 'c', ..., 'z'");
    }

    private void Expression_1() throws IsNotRightFormula {
        readNextSymbol();
        if (IsLetter(symbol)) Expression_2();
        else if (symbol == '[') {
            SquareExpression_0_1();
            if (IsSign(symbol)) SquareExpression_2();
            else throw new IsNotRightFormula(
                new Exception().getStackTrace()[0].getMethodName(),
                symbol, position, "'+', '-', '*', '/'");
        }
        else if (symbol == '(') {
            RoundExpression_0_1();
            if (IsSign(symbol)) RoundExpression_2();
            else throw new IsNotRightFormula(
                new Exception().getStackTrace()[0].getMethodName(),
                symbol, position, "'+', '-', '*', '/'");
        }
        else throw new IsNotRightFormula(
            new Exception().getStackTrace()[0].getMethodName(),
            symbol, position, "'[', '(', 'a', 'b', 'c', ..., 'z'");
    }

    private void Expression_2() throws IsNotRightFormula {
        readNextSymbol();
        if (IsSign(symbol)) Expression_0();
    }

    private void RoundExpression_0() throws IsNotRightFormula {
        if (symbol == '(') {
            Expression_1();
            if (symbol == ')') RoundExpression_1();
            else throw new IsNotRightFormula(
                new Exception().getStackTrace()[0].getMethodName(),
                symbol, position, "')'");
        }
        else throw new IsNotRightFormula(
            new Exception().getStackTrace()[0].getMethodName(),
            symbol, position, "'('");
    }

    private void RoundExpression_0_1() throws IsNotRightFormula {
        if (symbol == '(') {
            Expression_1();
            if (symbol == ')') readNextSymbol();
            else throw new IsNotRightFormula(
                new Exception().getStackTrace()[0].getMethodName(),
                symbol, position, "')'");
        }
        else throw new IsNotRightFormula(
            new Exception().getStackTrace()[0].getMethodName(),
            symbol, position, "'('");
    }

    private void RoundExpression_1() throws IsNotRightFormula {
        readNextSymbol();
        if (IsSign(symbol)) RoundExpression_2();
    }

    private void RoundExpression_2() throws IsNotRightFormula {
        readNextSymbol();
        if (symbol == '[') SquareExpression_0();
        else if (IsLetter(symbol)) Alphabet_0();
        else throw new IsNotRightFormula(
            new Exception().getStackTrace()[0].getMethodName(),
            symbol, position, "'[', 'a', 'b', 'c', ..., 'z'");
    }

    private void SquareExpression_0() throws IsNotRightFormula {
        if (symbol == '[') {
            Expression_1();
            if (symbol == ']') SquareExpression_1();
            else throw new IsNotRightFormula(
                new Exception().getStackTrace()[0].getMethodName(),
                symbol, position, "']'");
        }
        else throw new IsNotRightFormula(
            new Exception().getStackTrace()[0].getMethodName(),
            symbol, position, "'['");
    }

    private void SquareExpression_0_1() throws IsNotRightFormula {
        if (symbol == '[') {
            Expression_1();
            if (symbol == ']') readNextSymbol();
            else throw new IsNotRightFormula(
                new Exception().getStackTrace()[0].getMethodName(),
                symbol, position, "']'");
        }
        else throw new IsNotRightFormula(
            new Exception().getStackTrace()[0].getMethodName(),
            symbol, position, "'['");
    }

    private void SquareExpression_1() throws IsNotRightFormula {
        readNextSymbol();
        if (IsSign(symbol)) SquareExpression_2();
    }

    private void SquareExpression_2() throws IsNotRightFormula {
        readNextSymbol();
        if (symbol == '(') RoundExpression_0();
        else if (IsLetter(symbol)) Alphabet_1();
        else throw new IsNotRightFormula(
            new Exception().getStackTrace()[0].getMethodName(),
            symbol, position, "'(', 'a', 'b', 'c', ..., 'z'");
    }

    private void Alphabet_0() throws IsNotRightFormula {
        if (IsLetter(symbol)) RoundExpression_1();
        else throw new IsNotRightFormula(
            new Exception().getStackTrace()[0].getMethodName(),
            symbol, position, "'a', 'b', 'c', ..., 'z'");
    }

    private void Alphabet_1() throws IsNotRightFormula {
        if (IsLetter(symbol)) SquareExpression_1();
        else throw new IsNotRightFormula(
            new Exception().getStackTrace()[0].getMethodName(),
            symbol, position, "'a', 'b', 'c', ..., 'z'");
    }

    private boolean IsLetter(char sym) {
        for (char i : ALPHABET.toCharArray()) if (sym == i)
            return true;
        return false;
    }

    private boolean IsSign(char sym) {
        for (char i : SIGNS.toCharArray()) if (sym == i)
            return true;
        return false;
    }

    private void readNextSymbol() { symbol = formula.charAt(++position); }
    public String getResult() { return result; }
    public String getFormula() { return formula; }

//    public void infoEnter(String classname) {
//        i += 3;
//        String s = "";
//        for (int j = 0; j < i; ++j) s += " ";
//        System.out.println(s + "Вход в " + classname);
//    }
//    public void infoExit(String classname) {
//        String s = "";
//        for (int j = 0; j < i; ++j) s += " ";
//        System.out.println(s + "Выход из " + classname);
//        i -= 3;
//    }

    public static void reference() {
        System.out.println("""
                Правила пользования программой.
                Правильными считаются формулы, обладающие следующими свойтвами:
                После круглой скобки в строке всегда должна стоять квадратная (или никаких скобок),\s
                после квадратной - круглая (или никаких скобок).\s
                В правильной записи не могут присутствовать “лишние” (двойные) скобки,\s
                но одна буква может браться в скобки.

                Также в записи формул не допускается использование любых символов, кроме следущих:
                '[', ']', '(', ')', '*', '/', '+', '-', буквы латиницы
                Особенно следует учитывать, что в записи формул не могут присутствовать пробелы.
                Введите '0' для выхода из программы""");
    }
    private static class IsNotRightFormula extends Exception {
        IsNotRightFormula(String methodName, char symbol, int position, String allowedCharacters) {
            super ("Формула не удовлетворяет критериям: " +
                    "\nОбнаружен символ, отличный от допустимых знаков: " + allowedCharacters +
                    "\nСимвол: " + symbol +
                    "\nМесто обнаружения: " + methodName + ", позиция в формуле: " + (position + 1));
        }
    }
}
