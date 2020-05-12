package calculator.operators;

import calculator.node.Node;

import java.util.List;
import java.util.Stack;

/**
 * calculator.operators.ParenthesisLOperator
 * Created by HyunI Kim.
 * Date: 2020-05-02
 * Email: jbhyunikim@gmail.com
 */
public class ParenthesisLOperator extends Operator {
    public ParenthesisLOperator(String operator) {
        super(operator);
    }

    @Override
    public void infixToPostfix(Stack<Node> stack, List<Node> result) {
        stack.push(new Node(this));
    }

    @Override
    public void operate(Stack<Node> stack) { }
}
