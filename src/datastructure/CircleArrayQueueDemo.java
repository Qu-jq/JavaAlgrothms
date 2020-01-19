package datastructure;

import java.util.Scanner;

/**
 * 循环数组队列
 * Created by qjq on 2020/1/19 12:12
 */
public class CircleArrayQueueDemo {
    //测试
    public static void main(String[] args) {
        //创建一个队列
        CircleArrayQueue arrayQueue = new CircleArrayQueue(3);
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

class CircleArrayQueue {
    private int maxSize;//表示数组的最大容量
    private int front;//队列头  指向队列第一个元素
    private int rear;//队列尾 队列的最后一个有效元素的下一个元素
    private int[] arr;//该数组用于模拟队列存放数据

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];//初始化队列
        front = 0;//指向队列头部，指向队列第一个元素
        rear = 0;//指向队列的尾部，队列的最后一个有效元素的下一个元素
    }
    //判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize == front;
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
        arr[rear]=info;//和非循环队列不同
        rear = (rear+1)%maxSize;
    }

    //获取队列数据，出队
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，无法获取数据");
        }
        int value = arr[front];
        front = (front+1)%maxSize;
        return value;
    }

    //显示队列所有数据
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，无法获取数据");
        }
        //从front开始遍历，直到rear的前一个位置
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }

    //计算队列有效数据的个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }
    //显示队列头部信息，只是显示，不是出队
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，无法获取数据");
        }
        return arr[front];
    }
}
