package calculator.operators;

import calculator.node.Node;

import java.util.List;
import java.util.Stack;

/**
 * calculator.operators.ParenthesisROp
 * Created by HyunI Kim.
 * Date: 2020-05-02
 * Email: jbhyunikim@gmail.com
 */
public class ParenthesisROperator extends Operator {

    public ParenthesisROperator(String operator) {
        super(operator);
    }

    @Override
    public void infixToPostfix(Stack<Node> stack, List<Node> result) {
        while (!stack.isEmpty()) {
            Node top = stack.pop();
            Operator op = top.operator;

            if (op instanceof ParenthesisLOperator) break;

            result.add(top);
        }
    }

    @Override
    public void operate(Stack<Node> stack) { }
}
