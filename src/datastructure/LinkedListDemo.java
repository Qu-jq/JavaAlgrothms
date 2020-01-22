package datastructure;

/**
 * 链表练习
 * 增插删改查排
 * Created by qjq on 2020/1/22 10:57
 */
public class LinkedListDemo {
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
     * @param node
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
     * @param node
     * @return
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

    /**
     * 在指定位置插入节点
     * @param pos
     * @param node
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
        Node nodeTemp = null;
        nodeTemp = temp.pNext;  //前一个节点的指针域保存在nodeTemp中
        temp.pNext = node;      //前一个节点的指针域变为新插入节点的地址
        node.pNext = nodeTemp;  //新插入节点的指针域变为前一个节点temp的指针域
        //由此可见数据结构还是使用C语言，能够有个较清晰的认识
    }

    /**
     * 删除固定位置的节点
     * @param pos
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
     * @param pos
     * @param node
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
        Node nodeTemp = null;
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
        Node p = null;
        Node q = null;

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

}