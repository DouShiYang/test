package algorithm.node;


/**
 * 单向链表倒转
 */
public class SingleNodeReverse {

    public static class Node {

        String name;

        Node next;

        public Node(String name) {
            this.name = name;
        }
    }


    public static Node reverse(Node node) {
        Node pre = null;
        Node next = null;
        while(node!=null){
            //将当前节点的下一条记录到next指针
            next = node.next;
            //倒转当前节点的下一个节点为pre节点
            node.next = pre;
            //原pre指针 移动到node节点
            pre = node;
            //node指针 移动到next节点
            node = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node node = new Node("唐三");
        node.next = new Node("小舞");
        node.next.next = new Node("舞麟");
        node.next.next.next = new Node("蓝轩宇");
        Node reverse = reverse(node);
        System.out.println(reverse.name);
    }
}
