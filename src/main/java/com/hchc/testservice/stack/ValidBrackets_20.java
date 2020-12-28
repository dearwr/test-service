package com.hchc.testservice.stack;

import java.util.Stack;

/**
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 *
 * @author wangrong
 * @date 2020-09-26
 */
public class ValidBrackets_20 {

    public static void main(String[] args) {
        method1("()[]{}");
    }

    static boolean method1(String s) {
        if ((s.length() & 1) == 1) {
            return false;
        }
        Stack<String> preStack = new Stack();
        Stack<String> sufStack = new Stack();
        String str;
        for (int i = 0; i <= s.length() - 1; i++) {
            str = s.substring(i, i + 1);
            if ("{".equals(str) || "(".equals(str) || "[".equals(str)) {
                preStack.add(str);
            }
            str = s.substring(s.length() - 1 - i, s.length() - 1 - i + 1);
            if ("}".equals(str) || ")".equals(str) || "]".equals(str)) {
                sufStack.add(str);
            }
        }
        int preSize = preStack.size();
        int sufSize = sufStack.size();
        if (preSize != sufSize) {
            return false;
        }
        for (int i = 0; i < preSize; i++) {
            if ("{".equals(preStack.peek()) && !"}".equals(sufStack.peek())) {
                return false;
            } else if ("(".equals(preStack.peek()) && !")".equals(sufStack.peek())) {
                return false;
            } else if ("[".equals(preStack.peek()) && !"]".equals(sufStack.peek())) {
                return false;
            }
            preStack.pop();
            sufStack.pop();
        }
        return true;
    }
}
