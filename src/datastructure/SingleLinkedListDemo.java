package datastructure;


import java.util.Stack;

/**
 * 单向链表练习
 * 增插删改查排
 * Created by qjq on 2020/1/22 10:57
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addNode(new Node(1));
        linkedList.addNode(new Node(2));
        linkedList.addNode(new Node(3));
        linkedList.addNode(new Node(4));
        linkedList.showLinkedList();
        linkedList.insertNode(2,new Node(10));
        linkedList.showLinkedList();
        System.out.println("链表的长度为："+linkedList.lenLinkedList());
        linkedList.deleteNode(4);
        linkedList.showLinkedList();
        System.out.println("链表的长度为："+linkedList.lenLinkedList());
        //错误性测试
        linkedList.insertNode(5,new Node(10));
        linkedList.deleteNode(6);
        //修改测试
        linkedList.reviseNode(2,new Node(11));
        linkedList.showLinkedList();
        //排序测试
        System.out.println("排序结果：");
        linkedList.sortLinkedList();
        linkedList.showLinkedList();

        //查找倒数节点测试：
        int backPos = 2;
        System.out.printf("倒数第%d个节点是：%d",backPos,linkedList.searchBackNode(backPos).data);
        //翻转链表测试
        linkedList.reverseLinkedList(linkedList.getpHead());
        System.out.println();
        System.out.println("翻转后：");
        linkedList.showLinkedList();
        //反向显示测试
        System.out.println();
        System.out.println("反向显示：");
        linkedList.reverseShow1(linkedList.getpHead());
        System.out.println();
        linkedList.reverseShow2(linkedList.getpHead());

        //有序链表合并测试
        LinkedList linkedList2 = new LinkedList();
        linkedList2.addNode(new Node(1));
        linkedList2.addNode(new Node(3));
        linkedList2.addNode(new Node(4));
        linkedList2.addNode(new Node(7));
        linkedList2.showLinkedList();
        LinkedList linkedList3 = new LinkedList();
        linkedList3.addNode(new Node(3));
        linkedList3.addNode(new Node(4));
        linkedList3.addNode(new Node(5));
        linkedList3.addNode(new Node(10));
        linkedList3.showLinkedList();
        LinkedList.showLinkedList(LinkedList.mergeOrderedList(linkedList2.getpHead(),linkedList3.getpHead()));


    }
}

/**
 * 节点（c语言中为结构体）
 * 每个Node对象就是一个节点，与c语言不同，c语言需要自己分配空间，这里创建一个对象，就自动分配空间
 */
class Node{
    public Integer data;//数据域
    public Node pNext;//指针域

    public Node(Integer data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", pNext=" + pNext +
                '}';
    }
}

/**
 * 链表
 */
class LinkedList{
    //先初始化一个头节点，头节点不动，不存放具体数据
    private Node pHead = new Node(null);

    /**
     * 在链表末尾添加节点
     * @param node 添加的新节点
     */
    public void addNode(Node node){
        //设置一个辅助遍历
        Node temp = pHead;
        while(temp.pNext!=null){
            temp = temp.pNext;
        }
        temp.pNext = node;
    }

    /**
     * 判断链表是否为空
     * @param node 输入头节点
     * @return 返回真假
     */
    public boolean isEmpty(Node node){
        return node.pNext == null;
    }

    /**
     * 显示链表的数据
     */

    public void showLinkedList(){
        if(isEmpty(pHead)){
            System.out.println("链表为空");
            return;
        }
        Node temp = pHead;
        System.out.println("链表的数据为：");
        while(temp.pNext!=null){
            temp = temp.pNext;
            System.out.print(temp.data+"\t");
        }
        System.out.println();//换行
    }
    public static void showLinkedList(Node pHead){
        if(pHead.pNext == null){
            System.out.println("链表为空");
            return;
        }
        Node temp = pHead;
        System.out.println("链表的数据为：");
        while(temp.pNext!=null){
            temp = temp.pNext;
            System.out.print(temp.data+"\t");
        }
        System.out.println();//换行
    }

    /**
     * 链表长度
     */
    public int lenLinkedList(){
        Node temp = pHead;
        int len=0;
        while(temp.pNext!=null){
            temp = temp.pNext;
            len++;
        }
        return len;
    }
    public int lenLinkedList(Node pHead){
        Node temp = pHead;
        int len=0;
        while(temp.pNext!=null){
            temp = temp.pNext;
            len++;
        }
        return len;
    }

    /**
     * 在指定位置插入节点
     * @param pos 插入位置
     * @param node 新增节点
     */
    public void insertNode(int pos,Node node){
        //首先判断插入位置是否合理
        if(pos<1|pos>lenLinkedList()){
            System.out.println("插入节点的位置不正确");
            return;
        }
        //查找要插入节点的前一个节点 temp
        int i=0;
        Node temp = pHead;
        while (i<pos-1){
            i++;
            temp = temp.pNext;
        }
        //插入操作
        Node nodeTemp;
        nodeTemp = temp.pNext;  //前一个节点的指针域保存在nodeTemp中
        temp.pNext = node;      //前一个节点的指针域变为新插入节点的地址
        node.pNext = nodeTemp;  //新插入节点的指针域变为前一个节点temp的指针域
        //由此可见数据结构还是使用C语言，能够有个较清晰的认识
    }

    /**
     * 删除固定位置的节点
     * @param pos 删除节点的位置
     */
    public void deleteNode(int pos){
        //首先判断删除位置是否合理
        if(pos<1|pos>lenLinkedList()){
            System.out.println("删除节点的位置不正确");
            return;
        }
        //查找要删除节点的前一个节点 temp
        int i=0;
        Node temp = pHead;
        while (i<pos-1){
            i++;
            temp = temp.pNext;
        }
        //删除操作
        temp.pNext = temp.pNext.pNext;      //将删除节点的指针域传给前一个节点的指针域
    }

    /**
     * 修改固定位置的节点，即将此位置的节点替换为新的节点
     * @param pos 位置
     * @param node 节点
     */
    public void reviseNode(int pos,Node node){
        //首先判断修改位置是否合理
        if(pos<1|pos>lenLinkedList()){
            System.out.println("修改节点的位置不正确");
            return;
        }
        //查找要修改节点的前一个节点 temp
        int i=0;
        Node temp = pHead;
        while (i<pos-1){
            i++;
            temp = temp.pNext;
        }
        //修改操作
        Node nodeTemp;
        nodeTemp = temp.pNext.pNext;  //修改节点的指针域保存在nodeTemp中
        temp.pNext = node;      //前一个节点的指针域变为新插入节点的地址
        node.pNext = nodeTemp;  //新插入节点的指针域变为修改节点的下一个节点的地址
    }

    /**
     * 将列表数据排序
     */
    public void sortLinkedList(){
        int i, j, t;
        int len = lenLinkedList();
        Node p;
        Node q;

        for (i = 0,p = pHead.pNext;i < len - 1;p=p.pNext,i++)//最后一个不用遍历，所以len-1
        {
            for (j = i + 1, q=p.pNext;j < len;q=q.pNext,j++)
            {
                if(q.data > p.data)          //if (a[i] > a[j])
                {
                    t = p.data;//t = a[i];
                    p.data = q.data;//a[i] = a[j];
                    q.data=t;//a[j] = a[i];
                }

            }
        }
    }

    /**
     * 查找单链表中倒数第k个节点（新浪面试题）
     * @param backPos 倒数节点位置
     */
    public Node searchBackNode(int backPos){
        //首先判断查找位置是否合理
        int len = lenLinkedList();
        if(backPos<1|backPos>len){
            throw new RuntimeException("查找节点的位置不正确");
        }
        int i = 0;
        Node temp = pHead;
        //倒数backPos个节点相当于第len - backPos+1个节点
        while(i < len - backPos+1){
            i++;
            temp = temp.pNext;
        }
        return temp;
    }

    /**
     * 将单链表进行反转（腾讯面试题）
     * @param pHead 头节点
     */
    public void reverseLinkedList(Node pHead){
        //当链表为空或者长度为1时，无需翻转直接返回
        if(pHead.pNext==null || pHead.pNext.pNext == null){
            return;
        }
        Node curNode = pHead.pNext;//辅助节点
        Node nextNode;//当前节点的下一个节点
        Node newpHead = new Node(null);//新的链表头部
        while(curNode!=null){
            nextNode = curNode.pNext;//暂时保存当前节点的下一个节点
            //将当前节点插入到新链表的第一个节点位置
            curNode.pNext = newpHead.pNext;
            newpHead.pNext = curNode;
            curNode = nextNode;
        }
        pHead.pNext = newpHead.pNext;//将新链表的头节点换为原来的节点
    }

    public Node getpHead() {
        return pHead;
    }
    /**
     * 从尾到头打印单链表（百度面试题）
     * 方法一：循环遍历，逆序输出
     * @param pHead 头节点
     */
    public void reverseShow1(Node pHead){
        if (pHead.pNext == null){
            System.out.println("链表为空");
            return;
        }
        for (int i = 1; i < lenLinkedList()+1; i++) {
            int j=0;
            Node temp = pHead;
            while(j < lenLinkedList() - i+1){
                j++;
                temp = temp.pNext;
            }
            System.out.print(temp.data+"\t");
        }
    }
    /**
     * 从尾到头打印单链表（百度面试题）
     * 方法二：利用栈这个数据结构，将每个节点压入进去，利用栈先进后出的特点，逆序打印
     * @param pHead 头节点
     */
    public void reverseShow2(Node pHead){
        if (pHead.pNext == null){
            System.out.println("链表为空");
            return;
        }
        Node temp = pHead.pNext;//指向第一个节点
        Stack<Node> stack = new Stack<>();
        //压栈
        while (temp != null){
            stack.push(temp);
            temp=temp.pNext;
        }
        //出栈
        while(!stack.isEmpty()){
            System.out.print(stack.pop().data+"\t");
        }

    }
    /**
     * 合并两个有序单链表，合并之后的链表仍然是有序的（都是从小到大)
     * @param pHead1 第一个有序节点
     * @param pHead2 第二个有序节点
     */
    public static Node mergeOrderedList(Node pHead1,Node pHead2){
        Node newHead = new Node(null);//合并的新链表的头部
        Node temp1 = pHead1.pNext;//pHead1遍历
        Node temp2 = pHead2.pNext;//pHead2遍历
        Node newTemp = newHead;//新链表遍历
        if((temp1==null) && (temp2==null)){
            return null;
        }
        //当两个链表都不为空时
        while((temp1!=null) && (temp2!=null)){
            if(temp1.data<=temp2.data){
                newTemp.pNext=temp1;
                temp1 = temp1.pNext;//当pHead1链表的值较小时，将其传给新链表，自身向后移
            }
            else {
                newTemp.pNext = temp2;
                temp2 = temp2.pNext;//当pHead2链表的值较小时，将其传给新链表，自身向后移
            }

            newTemp = newTemp.pNext;
        }
        //当第一个链表先为空时
        if(temp1 == null){
            newTemp.pNext = temp2;//将第二个链表剩下的传给新链表即可
        }
        //当第二个链表先为空时
        if(temp2 == null){
            newTemp.pNext = temp1;//将第一个链表剩下的传给新链表即可
        }
        return newHead;
    }



}