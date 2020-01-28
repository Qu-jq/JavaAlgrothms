package datastructure;

import java.util.Scanner;

/**
 * 使用数组模拟栈
 * Created by qjq on 2020/1/28 16:29
 */
public class ArrayStackDemo {
    //测试
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        String mark;
        Scanner scanner = new Scanner(System.in);//键盘输入
        boolean flag = true;//循环标志位
        while(flag){
            System.out.println("show:显示栈");
            System.out.println("pop:出栈");
            System.out.println("push:入栈");
            System.out.println("exit:退出");
            System.out.println("请输入你的选择：");
            mark = scanner.next();
            switch (mark){
                case "show":
                    arrayStack.list();
                    break;
                case "pop":
                    try {
                        int res = arrayStack.pop();
                        System.out.println("出栈的数据是："+res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "push":
                    System.out.println("请输入入栈数据：");
                    int val = scanner.nextInt();
                    arrayStack.push(val);
                    break;
                case "exit":
                    flag=false;
                    break;
            }
        }
    }
}

/**
 * 创建模拟栈的类
 */
class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top;//模拟栈顶指针

    /**
     * 构造器
     * @param maxSize 数组的最大容量
     */
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
        top=-1;//初始值默认为-1
    }

    /**
     * 判断栈是否满
     * @return 真假
     */
    public boolean isFull(){
        return top == maxSize-1;
    }

    /**
     * 入栈
     * @param data 入栈数据
     */
    public void push(int data){
        if (isFull()){
            System.out.println("栈已满，无法入栈");
            return;
        }
        top++;
        stack[top] = data;
    }

    /**
     * 出栈
     * @return 返回出栈数据
     */
    public int pop(){
        if (top == -1){
            throw new RuntimeException("栈为空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历栈 从顶部开始
     */
    public void list(){
        if(top == -1){
            System.out.println("栈为空");
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
