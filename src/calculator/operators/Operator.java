package calculator.operators;

import calculator.node.Node;

import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * calculator.operators.Operator
 * Created by HyunI Kim.
 * Date: 2020-05-02
 * Email: jbhyunikim@gmail.com
 */
public abstract class Operator {
    protected final String[] operators;

    public Operator(String... operators) {
        this.operators = operators;
    }

    public boolean matches(String token) {
        for (String operator : operators)
            if (operator.equals(token))
                return true;
        return false;
    }

    public abstract void infixToPostfix(Stack<Node> stack, List<Node> result);
    public abstract void operate(Stack<Node> stack);

    @Override
    public String toString() {
        return String.join(",", operators);
    }
}
