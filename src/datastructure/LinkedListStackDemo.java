package datastructure;

import java.util.Scanner;

/**
 * 使用链表模拟栈
 * 注意此处链表的方向和以前的反过来
 * [头节点1|next=null]<-[节点2|next]<-[节点3|next]
 * 目的是便于出栈入栈
 * Created by qjq on 2020/1/28 17:02
 */
public class LinkedListStackDemo {
    public static void main(String[] args) {
        Calculate LLStack = new Calculate();
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
                    LLStack.list();
                    break;
                case "pop":
                    try {
                        LLNode2 res = LLStack.pop();
                        System.out.println("出栈的数据是："+res.data);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "push":
                    System.out.println("请输入入栈数据：");
                    int val = scanner.nextInt();
                    LLStack.push(new LLNode2(val));
                    break;
                case "exit":
                    flag=false;
                    break;
            }
        }
    }
}

/**
 * 链表节点
 */
class LLNode{
    Integer data;//数据域
    LLNode2 next;//指针域 默认为空

    public LLNode(Integer data) {
        this.data = data;
    }
}

class LinkedListStack{
    private LLNode2 top;//模拟栈顶指针
    private LLNode2 bottom;//模拟栈顶指针
    /**
     * 构造器：相当于初始化
     */
    public LinkedListStack() {
        bottom = new LLNode2(null);//栈的头节点
        top= bottom;
    }

    /**
     * 入栈
     * @param llNode 入栈节点
     */
    public void push(LLNode2 llNode){
        llNode.next = top;//新节点的next指向顶部
        top = llNode;//top重新指向最顶端
    }

    /**
     * 出栈
     * @return 返回出栈数据
     */
    public LLNode2 pop(){
        if (top == bottom){
            throw new RuntimeException("栈为空");
        }
        LLNode2 temp;
        temp = top;
        top = top.next;//重新指向上一个节点
        return temp;
    }


    /**
     * 遍历栈 从顶部开始
     */
    public void list(){
        if(top == bottom){
            System.out.println("栈为空");
        }
        LLNode2 temp = top;
        while (temp!=bottom){
            System.out.print(temp.data + "\t");
            temp = temp.next;
        }
        System.out.println();
    }
}