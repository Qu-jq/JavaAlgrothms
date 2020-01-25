package datastructure;

/**
 * 双向链表练习
 * 增删改查
 * Created by qjq on 2020/1/25 15:38
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleNode pHead = new DoubleNode(null);//创建一个头节点
        //插入节点
        DoubleLinkedList.addDoubleNode(pHead,new DoubleNode(1));
        DoubleLinkedList.addDoubleNode(pHead,new DoubleNode(2));
        DoubleLinkedList.addDoubleNode(pHead,new DoubleNode(3));
        DoubleLinkedList.addDoubleNode(pHead,new DoubleNode(4));
        //打印链表
        DoubleLinkedList.showLinkedList(pHead);
        //删除节点
        DoubleLinkedList.deleteDoubleNode(pHead,2);
        DoubleLinkedList.showLinkedList(pHead);
        //长度
        System.out.println("链表的长度是："+DoubleLinkedList.lenLinkedList(pHead));
        //修改
        DoubleLinkedList.reviseDoubleNode(pHead,3,new DoubleNode(12));
        DoubleLinkedList.showLinkedList(pHead);
        //删除最后一个节点
        DoubleLinkedList.deleteDoubleNode(pHead,3);
        DoubleLinkedList.showLinkedList(pHead);

    }
}

/**
 * 双向链表的节点
 */
class DoubleNode{
    Integer data;
    DoubleNode next;//指向后一个节点，默认为null
    DoubleNode prior;//指向前一个节点，默认为null
    public DoubleNode(Integer data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DoubleNode{" +
                "data=" + data +
                ", next=" + next +
                ", prior=" + prior +
                '}';
    }
}

class DoubleLinkedList{

    /**
     * 判断双向链表是否为空
     * @param pHead 头节点
     * @return 返回真假
     */
    public static boolean isEmpty(DoubleNode pHead){
        return pHead.next==null;
    }

    /**
     * 打印链表数据
     * @param pHead 头节点
     */
    public static void showLinkedList(DoubleNode pHead){
        if(isEmpty(pHead)){
            System.out.println("链表为空");
            return;
        }
        DoubleNode temp = pHead;
        System.out.println("链表的数据为：");
        while(temp.next!=null){
            temp = temp.next;
            System.out.print(temp.data+"\t");
        }
        System.out.println();//换行
    }

    /**
     * 链表尾部增加节点
     * @param pHead 头节点
     * @param doubleNode 新增双向节点
     */
    public static void addDoubleNode(DoubleNode pHead,DoubleNode doubleNode){
        DoubleNode temp = pHead;//用于移动的节点变量
        //找到最后一个节点
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = doubleNode;//最后一个节点的next指向新增节点
        doubleNode.prior = temp;//新增节点的prior指向最后一个节点
    }

    /**
     * 删除指定节点
     * @param pHead 头节点
     * @param pos 位置
     */
    public static void deleteDoubleNode(DoubleNode pHead, int pos){
        //检查删除位置是否合理
        int len = lenLinkedList(pHead);
        if(pos<1|pos>len){
            System.out.println("插入节点的位置不正确");
            return;
        }
        //找到要删除的节点
        int i=0;
        DoubleNode temp = pHead;
        while (i<pos){
            i++;
            temp = temp.next;
        }
        //删除操作
        temp.prior.next = temp.next;//删除节点的前一个节点的next指向删除节点的下一个节点
        //如果删除的是最后一个节点就不需要执行下面语句
        if(pos != len){
            temp.next.prior = temp.prior;//删除节点的下一个节点的prior指向删除节点的前一个节点
        }

    }

    /**
     * 链表长度
     * @param pHead 头节点
     */
    public static int lenLinkedList(DoubleNode pHead){
        DoubleNode temp = pHead;
        int len=0;
        while(temp.next!=null){
            temp = temp.next;
            len++;
        }
        return len;
    }

    /**
     * 修改固定位置的节点，即将此位置的节点替换为新的节点
     * @param pos 位置
     * @param node 节点
     */
    public static void reviseDoubleNode(DoubleNode pHead,int pos,DoubleNode node){
        //首先判断修改位置是否合理
        if(pos<1|pos>lenLinkedList(pHead)){
            System.out.println("修改节点的位置不正确");
            return;
        }
        //查找要修改的节点temp
        int i=0;
        DoubleNode temp = pHead;
        while (i<pos){
            i++;
            temp = temp.next;
        }
        //修改操作   这里只修改数据域
        temp.data = node.data;
    }

}
