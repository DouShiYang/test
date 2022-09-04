package algorithm.node;

/**
 * 双端链表实现栈
 */
public class DoubleNodeStack {

    public static class Node<V> {

        public V value;
        public Node<V> node;
        public Node<V> next;
        public Node<V> last;

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

        //尾部元素
        private Node<V> tail;

        private int size;

        public Myqueue() {
            this.head = null;
            this.size = 0;
            this.tail = null;
        }

        public Myqueue(Node<V> head, Node<V> tail, int size) {
            this.head = head;
            this.tail = tail;
            this.size = size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        /**
         * 从头部添加元素
         *
         * @param value
         */
        public void push(V value) {
            Node<V> curr = new Node<>(value);
            if (tail == null) {
                tail = curr;
                head = curr;
            } else {
                curr.next = tail;
                tail.last = curr;
                tail = curr;
            }
            size++;
        }

        /**
         * 弹出栈
         *
         * @return
         */
        public V poll() {
            V vns = null;
            if (tail != null) {
                vns = tail.value;
                tail = tail.next;
                size--;
            } else {
                tail = null;
            }
            return vns;
        }



        /**
         * 从栈中取出元素
         *
         * @return
         */
        public V peak() {
            V vns = null;
            if (tail != null) {
                vns = tail.value;
            }
            return vns;
        }


    }

    public static void main(String[] args) {
        Myqueue<Object> objectMyqueue = new Myqueue<>();
        objectMyqueue.push(3);
        objectMyqueue.push(5);
        objectMyqueue.push(6);
        Object poll = objectMyqueue.poll();
        System.out.println("弹出一个元素：" + poll);

        objectMyqueue.push(4);
        Object peak = objectMyqueue.peak();
        System.out.println("取出第一个元素：" + peak);


    }

}
