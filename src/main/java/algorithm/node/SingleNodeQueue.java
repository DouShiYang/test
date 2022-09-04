package algorithm.node;

/**
 * 单链表实现队列
 */
public class SingleNodeQueue {

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

        public void offer(V value) {
            Node<V> curr = new Node<>(value);
            if (tail == null) {
                tail = curr;
                head = curr;
            } else {
                tail.next = curr;
                tail = tail.next;
            }
            size++;
        }

        /**
         * 弹出
         * @return
         */
        public V poll() {
            V vns = null;
            if (head != null) {
                vns = head.value;
                head = head.next;
                size--;
            }else {
                tail = null;
            }
            return vns;
        }

        /**
         * 取出元素
         * @return
         */
        public V peak(){
            V vns = null;
            if(head!=null){
                vns = head.value;
            }
            return vns;
        }
    }

    public static void main(String[] args) {
        Myqueue<Object> objectMyqueue = new Myqueue<>();
        objectMyqueue.offer(3);

        objectMyqueue.offer(6);
        Object poll = objectMyqueue.poll();
        System.out.println("弹出一个元素："+ poll);

        objectMyqueue.offer(4);
        Object peak = objectMyqueue.peak();
        System.out.println("取出第一个元素："+ peak);


    }

}
