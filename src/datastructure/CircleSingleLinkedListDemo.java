package datastructure;

/**
 * 循环单向链表解决约瑟夫问题
 * Created by qjq on 2020/1/27 11:18
 */
public class CircleSingleLinkedListDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList cSLL = new CircleSingleLinkedList();
        cSLL.creat(5);
        cSLL.show();
        cSLL.outList(1,2);
    }
}

/**
 * 节点
 */
class CircleNode{
    Integer data;//数据域，编号
    CircleNode next;//指向下一个节点，默认为null

    public CircleNode(Integer data) {
        this.data = data;
    }
}
/**
 * 单向循环链表
 */
class CircleSingleLinkedList{
    //创建一个first的节点
    private CircleNode first = new CircleNode(null);

    /**
     * 创建一个单向循环链表
     * @param num 循环链表的节点个数
     */
    public void creat(int num){

        //数据校验
        if(num<1){
            System.out.println("个数不正确");
        }
        CircleNode curNode = null;//辅助节点，帮助构建环形链表
        //创建
        for (int i = 1; i <= num; i++) {
            CircleNode newNode = new CircleNode(i);
            //当为第一个节点时
            if(i==1){
                first = newNode;
                first.next = newNode;
                curNode = first;
            }else {
                curNode.next = newNode;
                newNode.next = first;//新增节点的next指向第一个节点
                curNode = newNode;//当前指针变为新增的节点
            }

        }

    }

    /**
     * 遍历整个链表
     */
    public void show(){
        //首先检查是否为空
        if(first == null){
            System.out.println("这是一个空链表");
            return;
        }
        CircleNode curNode = first;//辅助节点，帮助遍历环形链表
        //遍历整个链表
        boolean flag = true;
        while(flag){
            System.out.print(curNode.data+"\t");
            curNode = curNode.next;
            if (curNode == first){
                flag=false;
            }
        }
        System.out.println();

    }

    /**
     * 出圈顺序
     * @param startNum 从第几个开始数数
     * @param countNum 数几个出圈
     */
    public void outList(int startNum,int countNum){
        //数据校验
        if(startNum < 1 || countNum < 2){
            System.out.println("输入数据不正确");
            return;
        }
        CircleNode helper = first;//辅助指针
        // 指向first的前一个节点
        while(true){
            if(helper.next == first){
                break;
            }
            helper = helper.next;
        }
        //报数之前，先让helper,first移动startNum-1次
        for (int i = 0; i < startNum - 1; i++) {
            helper = helper.next;
            first = first.next;
        }
        //开始报数
        while (true){
            //当只剩下一个节点时，退出循环
            if(helper == first){
                break;
            }
            //找到countNum的位置
            for (int i = 0; i < countNum - 1; i++) {
                helper = helper.next;
                first = first.next;
            }
            //出圈
            System.out.printf("第%d号出圈\n",first.data);
            first = first.next;
            helper.next = first;

        }
        //打印最后剩下的一个
        System.out.printf("最后剩下%d号\n",first.data);
    }

}