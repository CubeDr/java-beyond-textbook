package calculator;

import calculator.node.Node;
import calculator.operators.BinaryOperator;
import calculator.operators.Operator;
import calculator.operators.ParenthesisLOperator;
import calculator.operators.ParenthesisROperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * calculator.Calculator
 * Created by HyunI Kim.
 * Date: 2020-05-01
 * Email: jbhyunikim@gmail.com
 */
public class Calculator {

    public double calculate(String rawExpression) throws InvalidOperatorException {
        List<Node> infixExpression = separateExpression(rawExpression);
        List<Node> postfixExpression = infixToPostfix(infixExpression);
        for (Node token : postfixExpression)
            System.out.println(token);

        // 결과 반환
        return 0;
    }

    private List<Operator> operators = new ArrayList<Operator>() {{
        add(new BinaryOperator(1, (a, b) -> a + b, "+", "plus"));
        add(new BinaryOperator(1, (a, b) -> a - b, "-"));
        add(new BinaryOperator(2, (a, b) -> a * b, "*"));
        add(new BinaryOperator(2, (a, b) -> a / b, "/"));
        add(new ParenthesisLOperator("("));
        add(new ParenthesisROperator(")"));
    }};

    private Operator getOperator(String operator) {
        for (Operator op : operators)
            if (op.matches(operator))
                return op;
        return null;
    }

    private void addValue(List<Node> list, String valueToken) {
        list.add(new Node(Double.parseDouble(valueToken)));
    }

    private void addOperator(List<Node> list, String operator) throws InvalidOperatorException {
        Operator op = getOperator(operator);

        if (op == null)
            throw new InvalidOperatorException(operator);

        list.add(new Node(op));
    }

    // "1+2"  => 1 "+" 2
    // "12345+1" => 12345 "+" 1
    private List<Node> separateExpression(String rawExpression) throws InvalidOperatorException {
        List<Node> separated = new ArrayList<>();

        String token = "";

        for (char c : rawExpression.toCharArray()) {
            if (Character.isDigit(c)) {
                // token 이 숫자이면 이어 붙이기
                if (!isNumeric(token)) {
                    // 아니면 저장 후 새로 token 부여
                    addOperator(separated, token);
                    token = "";
                }
            } else {
                if (isNumeric(token)) {
                    // 숫자이면 저장 후 새로 token 부여
                    addValue(separated, token);
                    token = "";
                } else {
                    // token 이 숫자가 아니면 연산자 검사해보고 아직 안나왔으면 이어붙이기
                    try {
                        addOperator(separated, token);
                        token = "";
                    } catch (InvalidOperatorException ignored) {}
                }
            }

            token += c;
        }

        if (!token.isEmpty()) {
            try {
                addValue(separated, token);
            } catch (Exception e) {
                addOperator(separated, token);
            }
        }

        return separated;
    }

    private List<Node> infixToPostfix(List<Node> infixExpression) {
        Stack<Node> stack = new Stack<>();
        List<Node> result = new ArrayList<>();

        for (Node node : infixExpression) {
            switch (node.type) {
                case VALUE:
                    result.add(node);
                    break;
                case OPERATOR:
                    node.operator.infixToPostfix(stack, result);
                    break;
            }
        }

        while (!stack.isEmpty())
            result.add(stack.pop());

        return result;
    }

    private boolean isNumeric(String token) {
        for (char c : token.toCharArray())
            if (!Character.isDigit(c))
                return false;
        return true;
    }

    public static class InvalidOperatorException extends Exception {
        public final String operator;

        public InvalidOperatorException(String operator) {
            this.operator = operator;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expression = in.next();
        in.close();

        Calculator calculator = new Calculator();
        // 계산기 설정
        try {
            System.out.println(calculator.calculate(expression));
        } catch (InvalidOperatorException e) {
            System.out.println("잘못된 연산자 입니다. : " + e.operator);
        }
    }
}
