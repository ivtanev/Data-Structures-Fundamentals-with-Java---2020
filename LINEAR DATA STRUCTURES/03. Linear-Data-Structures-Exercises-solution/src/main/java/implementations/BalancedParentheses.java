package implementations;

import interfaces.Solvable;

import java.util.Stack;

public class BalancedParentheses implements Solvable {
    private String parentheses;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
    }

    public Boolean solve1() {
        if (this.parentheses.isEmpty() || this.parentheses.length() % 2 == 1) {
            return false;
        } else {
            for (int i = 0; i < this.parentheses.length() / 2; i++) {
                char first = this.parentheses.charAt(i);
                if (first != '{' && first != '(' && first != '[') {
                    return false;
                }
                char second = this.parentheses.charAt(parentheses.length() - 1 - i);

                switch (first) {
                    case '{':
                        if (second != '}') {
                            return false;
                        }
                        break;
                    case '[':
                        if (second != ']') {
                            return false;
                        }
                        break;
                    case '(':
                        if (second != ')') {
                            return false;
                        }
                        break;
                }
            }
        }
        return true;
    }

    public Boolean solve(){
        Stack<Character> stack = new Stack<>();

        if(this.parentheses.length() % 2 == 1){
            return false;
        }

        for (int i = 0; i < this.parentheses.length(); i++) {
            char expression = this.parentheses.charAt(i);
            if(expression == '{' || expression == '(' || expression == '['){
                stack.push(expression);
            }else {
                if(stack.isEmpty()){
                    return false;
                }else if(!isMatchingPair(stack.pop(), expression)){
                    return false;
                }
            }
        }
        if(stack.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    private boolean isMatchingPair(char character1, char character2) {
        if (character1 == '(' && character2 == ')')
            return true;
        else if (character1 == '{' && character2 == '}')
            return true;
        else return character1 == '[' && character2 == ']';
    }
}
