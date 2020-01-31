package datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 加入中缀表达式转后缀表达式后计算
 * Created by qjq on 2020/1/31 10:09
 */
public class PolandNotationPro {
    public static void main(String[] args) {
        //String mediumExpression = "(3+4)*5-6";//"3 4 + 5 * 6 -"
        String mediumExpression = "1+((2+3)*4)-5";//[1,2,3,+,4,*,+,5,–]
        List<String> suffixExpression;
        List<String> expression = toArrayList(mediumExpression);
        System.out.println("mediumExpression="+expression);
        suffixExpression = toSuffixExpression(mediumExpression);
        System.out.println("suffixExpression="+suffixExpression);
        int result = calculate(suffixExpression);
        System.out.println("结果为："+result);

    }

    /**
     * 将任意格式字符串转换为list，方便遍历
     * @param suffixExpression 逆波兰表达式
     * @return 返回ArrayList
     */
    public static List<String> toArrayList(String suffixExpression){
        List<String> expression = new ArrayList<>();
        char c;//用于遍历suffixExpression
        String str;//用于拼接字符
        int i=0;
        while (i < suffixExpression.length()){
            c = suffixExpression.charAt(i);
            //0的ASCII码对应48，9的ASCII码对应57,空格的ASCII码32，回车13
            //换行10
            if (c == 32||c == 13||c == 10){
                //如果是空格、回车、换行不做处理，略过
                i++;
            }else if (c < 48 || c > 57) {//当不是数字时
                expression.add(""+c);//直接放入list中
                i++;
            }else{//当为一个数时，需要考虑多位数
                str = "";
                while (i < suffixExpression.length() && (c=suffixExpression.charAt(i)) >= 48 && (c=suffixExpression.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                expression.add(str);
            }
        }
        return expression;
    }

    /**
     * 计算后缀表达式
     * @param expression 后缀表达式
     * @return 计算结果
     */
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

    /**
     * 加减乘除的计算
     * @param num1 数1
     * @param num2 数2
     * @param opera 运算符
     * @return 结果
     */
    public static int singleCal(Integer num1,Integer num2,String opera){
        if(opera.equals("+")) return num1+num2;
        if(opera.equals("-")) return num2-num1;
        if(opera.equals("*")) return num1*num2;
        if(opera.equals("/")) return num2/num1;
        throw new RuntimeException("运算错误");
    }

    public static List<String> toSuffixExpression(String mediumExpression){
        List<String> mediumExp = toArrayList(mediumExpression);
        Stack<String> operaStack = new Stack<>();//运算符栈
        //说明:因为tempStack这个栈后续没有pop操作，并且还需要逆序输出，为了方便此处使用List
        //Stack<String> tempStack = new Stack<>();//储存中间结果栈
        List<String> tempList = new ArrayList<>();

        for (String ele :
                mediumExp) {
            //遇到数字时，将数字压入tempList
            if(ele.matches("\\d+")){//匹配多位数
                tempList.add(ele);
            }else {//4) 遇到运算符时，比较其与operaStack栈顶运算符的优先级：
                //1.如果operaStack为空，或运算符为左括号“(”，则直接将此运算符入栈；
                if(operaStack.isEmpty()||ele.equals("(")){
                    operaStack.push(ele);
                }else if (ele.equals(")")){
                    //如果是右括号“)”，则依次弹出operaStack栈顶的运算符，
                    // 并压入tempList，直到遇到左括号为止，此时将这一对括号丢弃
                    String str;
                    while(!(str = operaStack.pop()).equals("(")){
                        tempList.add(str);
                    }
                }else {//2.为+-/*时，若优先级比栈顶运算符的高，也将运算符压入operaStack；
                    //当前优先级小于等于栈顶运算符时
                    while(operaStack.size()!=0 && isPrior(ele)<=isPrior(operaStack.peek())){
                        tempList.add(operaStack.pop());
                    }
                    //还需要ele压入operaStack
                    operaStack.push(ele);
                }

            }

        }
        //将operaStack中剩余的运算符依次弹出并压入tempList
        while(!operaStack.isEmpty()){
            tempList.add(operaStack.pop());
        }
        return tempList;
    }
    /**
     * 判断运算符优先级 等级越高，返回数字就大
     * 注意： “（”不算运算操作符，返回-1
     * @param str 运算符
     * @return 大小
     */
    public static int isPrior(String str){
        switch (str) {
            case "*":
            case "/":
                return 1;
            case "+":
            case "-":
                return 0;
            case "(":
                return -1;
            default:
                throw new RuntimeException("符号错误");//当输入不是运算操作符时，抛出异常

        }
    }

}
