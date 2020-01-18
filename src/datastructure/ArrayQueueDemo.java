package datastructure;

import java.util.Scanner;

/**
 * 使用数组模拟队列
 * 存在的问题：
 * 数组只能利用一次，不能重复利用（应该使用循环数组解决）
 * Created by qjq on 2020/1/18 19:57
 */
public class ArrayQueueDemo {
    //测试
    public static void main(String[] args) {
        //创建一个队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key;//用于接收用户输入
        Scanner scanner = new Scanner(System.in);//控制台输入
        boolean loop = true;
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出队列");
            System.out.println("a(add):添加队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):显示队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符串
            switch (key){
                case 's':
                    try{
                        arrayQueue.showQueue();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("请输入需要插入的数据：");
                    int value = scanner.nextInt();
                    try{
                        arrayQueue.addQueue(value);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try{
                        int val = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",val);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int val = arrayQueue.headQueue();
                        System.out.printf("取出的队列头的数据是%d\n",val);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class ArrayQueue {
    private int maxSize;//表示数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数组用于模拟队列存放数据

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];//初始化队列
        front = -1;//指向队列头部，指向队列头的前一个位置
        rear = -1;//指向队列的尾部，指向队列的最后一个数据（即就是队列最后一个数据）
    }
    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize-1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int info){
        if(isFull()){
            throw new RuntimeException("列表已满，不能插入数据");
        }
        rear++;
        arr[rear]=info;
    }

    //获取队列数据，出队
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，无法获取数据");
        }
        front++;
        return arr[front];
    }

    //显示队列所有数据
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，无法获取数据");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    //显示队列头部信息，只是显示，不是出队
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，无法获取数据");
        }
        return arr[front+1];
    }
}
