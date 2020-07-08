package implementations;

import org.junit.Test;

import static org.junit.Assert.*;

public class BalancedParenthesesTest {
    @Test
    public void zeroTestOne() {
        String input = "{[()]}";
        Boolean solve = new BalancedParentheses(input).solve();
        assertNotNull(solve);
        assertTrue(solve);
    }

    @Test
    public void zeroTestTwo() {
        String input = "{[(])}";
        Boolean solve = new BalancedParentheses(input).solve();
        assertNotNull(solve);
        assertFalse(solve);
    }

    @Test
    public void zeroTestThree(){
        String input = "{(}})}";
        Boolean solve = new BalancedParentheses(input).solve();
        assertNotNull(solve);
        assertFalse(solve);
    }

    @Test
    public void zeroTestFour(){
        String input = "";
        Boolean solve = new BalancedParentheses(input).solve();
        assertNotNull(solve);
        assertFalse(solve);
    }

    @Test
    public void zeroTestFive(){
        String input = "(()";
        Boolean solve = new BalancedParentheses(input).solve();
        assertNotNull(solve);
        assertFalse(solve);
    }

    @Test
    public void zeroTestSix(){
        String input = "(((([]))))";
        Boolean solve = new BalancedParentheses(input).solve();
        assertNotNull(solve);
        assertTrue(solve);
    }
}