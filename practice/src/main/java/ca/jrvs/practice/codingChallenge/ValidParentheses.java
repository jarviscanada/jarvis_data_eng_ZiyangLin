package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Stack;

/**
 * ticket: https://www.notion.so/Valid-Parentheses-2327724993e84d099487631596262df4
 */
public class ValidParentheses {

    /**
     * Big-O: Time - O(n) since n for for-loop pass, O(1) for stack operations, Space - O(n)
     * Justification: use a stack to record all open brackets encountered, so the most recent one will always
     *                stay at top of the stack and will be popped first. Once a close bracket is found, check
     *                if a corresponding open bracket is at top of the stack, then pop out that element. If not,
     *                then it means whether there is no corresponding open bracket or the open bracket is
     *                misplaced, in such case return false. The algorithm returns true if all characters are
     *                checked.
     */
    public static boolean isValidParentheses(String input) {
        // use a map to store valid pairs of parentheses for checking purpose.
        HashMap<Character, Character> pairsMap = new HashMap<>();
        pairsMap.put('}', '{');
        pairsMap.put(')', '(');
        pairsMap.put(']', '[');

        Stack<Character> parentheses = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if (pairsMap.containsKey(input.charAt(i))) {
                char topElement;
                // in case the current stack is empty, stack.pop() won't return an element, so set a dummy one
                if (parentheses.empty()) {
                    topElement = '?';
                } else {
                    topElement = parentheses.pop();
                }
                // if there is no open bracket found
                if (topElement != pairsMap.get(input.charAt(i))) {
                    return false;
                }
            } else if (input.charAt(i) == '{' || input.charAt(i) == '[' || input.charAt(i) == '(') {
                parentheses.push(input.charAt(i));
            }
        }

        return true;
    }
}
