package datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 利用波兰后缀表达式实现计算
 * Created by qjq on 2020/1/30 16:49
 */
public class PolandNotation {
    public static void main(String[] args) {
        //(3+4)*5-6 对应的后缀表达式就是 3 4 + 5 * 6 -
        //表达式中间用空格隔开，方便后面使用
        String suffixExpression = "3 4 + 5 * 6 -";
        List<String> expression;
        expression = toArrayList(suffixExpression);
        System.out.println("expression="+expression);
        int result = calculate(expression);
        System.out.println("结果为："+result);
    }

    /**
     * 将字符串转换为list，方便遍历
     * @param suffixExpression 逆波兰表达式
     * @return 返回ArrayList
     */
    public static List<String> toArrayList(String suffixExpression){
        String[] split = suffixExpression.split(" ");//以空格分隔
        List<String> expression = new ArrayList<>();
        for (String ele :
                split) {
            expression.add(ele);
        }
        return expression;
    }

    public static int calculate(List<String> expression){
        Stack<String> stack = new Stack<>();
        for (String ele :
                expression) {
            //遇到数字时，将数字压入堆栈
            if(ele.matches("\\d+")){//匹配多位数
                stack.push(ele);
            }else {
                //遇到运算符时，弹出栈顶的两个数，
                // 用运算符对它们做相应的计算（次顶元素 和 栈顶元素），
                // 并将结果入栈
                Integer num1 = Integer.parseInt(stack.pop());
                Integer num2 = Integer.parseInt(stack.pop());
                int cal = singleCal(num1, num2, ele);
                stack.push(Integer.toString(cal));
            }

        }
        return Integer.parseInt(stack.pop());
    }

    public static int singleCal(Integer num1,Integer num2,String opera){
        if(opera.equals("+")) return num1+num2;
        if(opera.equals("-")) return num2-num1;
        if(opera.equals("*")) return num1*num2;
        if(opera.equals("/")) return num2/num1;
        throw new RuntimeException("运算错误");
    }


}
