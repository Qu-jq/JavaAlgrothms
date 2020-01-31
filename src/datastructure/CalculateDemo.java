package datastructure;

import java.util.Scanner;

/**
 * 使用栈实现计算器功能
 * Created by qjq on 2020/1/30 10:02
 */
public class CalculateDemo {
    public static void main(String[] args) {
        Calculate<Character> operaStack = new Calculate<>();//存放运算符的栈
        Calculate<Integer> numStack = new Calculate<>();//存放数字的栈
        Scanner scanner = new Scanner(System.in);//键盘输入
        String calculateStr;//计算式子
        Character temp;//遍历计算式子
        boolean flag = true;//循环标志位
        boolean flag2 = true;//循环标志位
        System.out.println("请输入运算式：");
        calculateStr = scanner.next();
        System.out.println(calculateStr);
        //各个数据入栈
        //for (int i = 0; i < calculateStr.length(); i++) {
        int i =0;
        while (flag){
            temp = calculateStr.charAt(i);//读取计算式的第i个字符。
            //如果是数的话 直接入数栈
            if(!numStack.isOperation(temp)){
                //这里需要减去'0'，不然的话，转换的是为ASCII码
                numStack.push(new LLNode2<>((int) (temp-'0')));
            }else if(operaStack.isOperation(temp)){     //当为符号时
                //如果发现当前的符号栈为 空，就直接入栈
                if(operaStack.isEmpty()){
                    operaStack.push(new LLNode2<>(temp));
                    //此次循环结束，进行下一个符号
                    if (i == calculateStr.length() - 1) {
                        flag = false;
                    } else {
                        i++;
                    }
                    continue;
                }
                /*如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符，
                 就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，
                 然后将当前的操作符再次与栈顶操作符比较（注意）； 如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
                 */
                if(operaStack.isPrior(temp)<=operaStack.isPrior(operaStack.readLastData())){
                    Integer num1 = numStack.pop().data;
                    Integer num2 = numStack.pop().data;
                    Character opera = operaStack.pop().data;
                    Integer result = operaStack.calculate(num1,num2,opera);
                    numStack.push(new LLNode2<>(result));
                    continue;//此符号，再与下一个栈顶符号比较，因此这里不加入i++
                }else {
                    operaStack.push(new LLNode2<>(temp));
                }
            }
            //此符号结束，进行下一个符号
            if(i != (calculateStr.length() - 1)) {
                i++;
            } else {
                flag = false;
            }
        }
        numStack.list();
        operaStack.list();
        //出栈计算
        while(flag2){
            Integer num1 = numStack.pop().data;
            Integer num2 = numStack.pop().data;
            //当数据全部出栈完时，循环结束
            if (numStack.isEmpty()){
                flag2 = false;
            }
            Character opera = operaStack.pop().data;
            Integer result = operaStack.calculate(num1,num2,opera);
            //将最后结果压入栈中
            numStack.push(new LLNode2<>(result));
        }
        System.out.println("最后结果为："+numStack.readLastData());
    }
}

/**
 * 链表节点
 */
class LLNode2 <T>{
    T data;//数据域
    LLNode2<T> next;//指针域 默认为空

    public LLNode2(T data) {
        this.data = data;
    }
}

class Calculate<T> {
    private LLNode2<T> top;//模拟栈顶指针
    private LLNode2<T> bottom;//模拟栈顶指针
    /**
     * 构造器：相当于初始化
     */
    public Calculate() {
        bottom = new LLNode2<>(null);//栈的头节点
        top= bottom;
    }

    /**
     * 判断栈是否空
     * @return 真假
     */
    public boolean isEmpty(){
        return top == bottom;
    }

    /**
     * 入栈
     * @param llNode 入栈节点
     */
    public void push(LLNode2<T> llNode){
        llNode.next = top;//新节点的next指向顶部
        top = llNode;//top重新指向最顶端
    }

    /**
     * 出栈
     * @return 返回出栈节点
     */
    public LLNode2<T> pop(){
        if (top == bottom){
            throw new RuntimeException("栈为空");
        }
        LLNode2<T> temp;
        temp = top;
        top = top.next;//重新指向上一个节点
        return temp;
    }

    /**
     * 读取栈顶的数据，仅仅读取，不是出栈
     * @return 返回栈顶数据
     */
    public T readLastData(){
        if (top == bottom){
            throw new RuntimeException("栈为空");
        }
        return top.data;
    }

    /**
     * 遍历栈 从顶部开始
     */
    public void list(){
        if(top == bottom){
            System.out.println("栈为空");
            return;
        }
        LLNode2<T> temp = top;
        while (temp!=bottom){
            System.out.print(temp.data + "\t");
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * 判断是否为运算操作符
     * @param str 符号
     * @return 真假
     */
    public boolean isOperation(Character str){
        return str == '*' || str == '/' || str == '+' || str == '-';
    }

    /**
     * 判断运算符优先级 等级越高，返回数字就大
     * @param str 运算符
     * @return 大小
     */
    public int isPrior(Character str){
        if(str == '*' || str == '/'){
            return 1;
        } else if (str == '+' || str == '-'){
            return 0;
        } else {
            throw new RuntimeException("符号错误");//当输入不是运算操作符时，抛出异常
        }
    }

    public int calculate(Integer num1,Integer num2,Character opera){
        if(opera == '+') return num1+num2;
        if(opera == '-') return num2-num1;
        if(opera == '*') return num1*num2;
        if(opera == '/') return num2/num1;
        throw new RuntimeException("运算错误");
    }
}