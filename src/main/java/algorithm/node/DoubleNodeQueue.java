package algorithm.node;

import java.rmi.UnexpectedException;

/**
 * 双端链表实现队列
 */
public class DoubleNodeQueue {

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
        public void offerHead(V value) {
            Node<V> curr = new Node<>(value);
            if (tail == null) {
                tail = curr;
                head = curr;
            } else {
                curr.next = head;
                head.last = curr;
                head = curr;
            }
            size++;
        }

        /**
         * 从尾部加
         *
         * @param value
         */
        public void offerTail(V value) {
            Node<V> curr = new Node<>(value);
            if (tail == null) {
                tail = curr;
                head = curr;
            } else {
                tail.next = curr;
                curr.last = tail;
                tail = curr;
            }
            size++;
        }

        /**
         * 从头部弹出
         *
         * @return
         */
        public V pollHead() {
            V vns = null;
            if (head != null) {
                vns = head.value;
                head = head.next;
                size--;
            } else {
                tail = null;
            }
            return vns;
        }

        /**
         * 从尾部弹出
         *
         * @return
         */
        public V pollTail() {
            V vns = null;

            if (head == null) {
                return vns;
            }
            size--;
            vns = tail.value;

            if (tail == head) {
                tail = null;
                head = null;
            } else {
                vns = tail.value;
                tail = tail.last;
                tail.next = null;
            }
            return vns;
        }

        /**
         * 从头部取出元素
         *
         * @return
         */
        public V peakHead() {
            V vns = null;
            if (head != null) {
                vns = head.value;
            }
            return vns;
        }

        /**
         * 从尾部取出元素
         *
         * @return
         */
        public V peakTail() {
            V vns = null;
            if (tail != null) {
                vns = tail.value;
            }
            return vns;
        }
    }

    public static void main(String[] args) {
        Myqueue<Object> objectMyqueue = new Myqueue<>();
        objectMyqueue.offerTail(3);
        objectMyqueue.offerTail(5);
        objectMyqueue.offerHead(6);
        Object poll = objectMyqueue.pollHead();
        System.out.println("弹出一个元素：" + poll);

        objectMyqueue.offerHead(4);
        Object peak = objectMyqueue.peakTail();
        System.out.println("取出第一个元素：" + peak);


    }

}
