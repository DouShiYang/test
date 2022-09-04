package algorithm.node;

/**
 * 单链表实现栈
 */
public class SingleNodeStack {

    public static class Node<V> {

        public V value;
        public Node<V> node;
        public Node<V> next;

        public Node(V value, Node<V> node) {
            this.value = value;
            this.node = node;
        }

        public Node(V value) {
            this.value = value;
        }
    }

    public static class Myqueue<V> {
        //头部元素
        private Node<V> head;

        private int size;

        public Myqueue() {
            this.head = null;
            this.size = 0;
        }

        public Myqueue(Node<V> head, int size) {
            this.head = head;
            this.size = size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void push(V value) {
            Node<V> curr = new Node<>(value);
            if (head != null) {
                curr.next = head;
            }
            head = curr;
            size++;
        }

        /**
         * 弹出
         *
         * @return
         */
        public V poll() {
            V vns = null;
            if (head != null) {
                vns = head.value;
                head = head.next;
                size--;
            }
            return vns;
        }

        /**
         * 取出元素
         *
         * @return
         */
        public V peak() {
            V vns = null;
            if (head != null) {
                vns = head.value;
            }
            return vns;
        }
    }

    public static void main(String[] args) {
        Myqueue<Object> objectMyqueue = new Myqueue<>();
        objectMyqueue.push(3);
        objectMyqueue.push(6);
        objectMyqueue.push(7);
        objectMyqueue.push(4);
        System.out.println("取出第一个元素：" + objectMyqueue.poll());
        System.out.println("取出第一个元素：" + objectMyqueue.poll());
        System.out.println("取出第一个元素：" + objectMyqueue.poll());
        System.out.println("取出第一个元素：" + objectMyqueue.poll());
        System.out.println("取出第一个元素：" + objectMyqueue.poll());
    }

}
