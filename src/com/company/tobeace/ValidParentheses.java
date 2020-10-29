package com.company.tobeace;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {
    public static boolean isValid(String s)  {
        HashMap<Character,Character>hashMap=new HashMap<>();
        hashMap.put(')','(');
        hashMap.put(']','[');
        hashMap.put('}','{');
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<s.length();i++){
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[' ){
                stack.add(s.charAt(i)); // 入栈
            } else if (!stack.isEmpty() && hashMap.get(s.charAt(i)) == stack.peek()){   // 循环中，stack不能为空    // 栈中有数据，且此元素与栈尾元素相同
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();   // 循环结束，栈中还有数据则 false
    }

    public static void main(String[] args) {
        String s="{}{}{}{}{}{(})()()";
        System.out.println(isValid(s));
    }
}
