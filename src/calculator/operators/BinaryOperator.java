package calculator.operators;

/**
 * calculator.operators.BinaryOperator
 * Created by HyunI Kim.
 * Date: 2020-05-09
 * Email: jbhyunikim@gmail.com
 */
public class BinaryOperator extends PrecedenceOperator {
    private final BinaryOperation operation;

    public BinaryOperator(int precedence, BinaryOperation operation, String... operator) {
        super(precedence, operator);
        this.operation = operation;
    }
}
