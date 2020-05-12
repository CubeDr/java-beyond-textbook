package calculator.node;

import calculator.operators.Operator;

/**
 * calculator.node.Node
 * Created by HyunI Kim.
 * Date: 2020-05-01
 * Email: jbhyunikim@gmail.com
 */
public class Node {
    // 숫자를 저장하는지, 연산자를 저장하는지
    public final NodeType type;
    // 숫자를 저장하기 위한 필드
    public final double value;
    // 연산자를 저장하기 위한 필드
    public final Operator operator;

    // 숫자를 저장하는 Node 를 생성
    public Node(double value) {
        this(NodeType.VALUE, value, null);
    }

    // 연산자를 저장하는 Node 를 생성
    public Node(Operator operator) {
        this(NodeType.OPERATOR, 0, operator);
    }

    private Node(NodeType type, double value, Operator operator) {
        this.type = type;
        this.value = value;
        this.operator = operator;
    }

    @Override
    public String toString() {
        switch (type) {
            case VALUE: return Double.toString(value);
            case OPERATOR: return operator.toString();
            default: throw new RuntimeException("NodeType 이 이상해요!");
        }
    }
}
