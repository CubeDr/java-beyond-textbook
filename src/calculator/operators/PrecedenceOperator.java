package calculator.operators;

import calculator.node.Node;

import java.util.List;
import java.util.Stack;

/**
 * calculator.operators.PrecedenceOperator
 * Created by HyunI Kim.
 * Date: 2020-05-02
 * Email: jbhyunikim@gmail.com
 */
public class PrecedenceOperator extends Operator {
    public final int precedence;

    public PrecedenceOperator(int precedence, String... operator) {
        super(operator);
        this.precedence = precedence;
    }

    @Override
    public void infixToPostfix(Stack<Node> stack, List<Node> result) {
        // 1. Stack 에서 자신보다 우선순위가 같거나 높은 연산자를 pop -> result 에 넣기
        while (!stack.isEmpty()) {
            Node top = stack.peek();
            Operator op = top.operator;

            if (!(op instanceof PrecedenceOperator)) break;

            PrecedenceOperator precedenceOperator = (PrecedenceOperator) op;
            int precedence = precedenceOperator.precedence;

            if (precedence >= this.precedence)
                result.add(stack.pop());
            else break;
        }
        // 2. 자기 자신을 stack 에 push
        stack.push(new Node(this));
    }

    @Override
    public void operate(Stack<Node> stack) {
        
    }
}
