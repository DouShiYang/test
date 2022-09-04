package algorithm.node;

import javax.management.ListenerNotFoundException;
import java.util.List;

/**
 * K个节点内的链表进行逆序排列 返回最新的链表
 * a->b->c->d->e
 * 两个节点内的做到排序
 * b->a->d->c->e
 */
public class NodeDescSort {

    public static class ListNode<V> {

        public V value;
        public ListNode<V> node;
        public ListNode<V> next;
        public ListNode<V> last;

        public ListNode(V value, ListNode<V> node) {
            this.value = value;
            this.node = node;
        }

        public ListNode(V value) {
            this.value = value;
        }
    }

    /**
     * 返回node节点后的第k个节点
     */
    public static ListNode getKGroupEnd(ListNode node, int k) {
        while (--k != 0 && node != null) {
            node = node.next;
        }
        return node;
    }

    public static void reverse(ListNode start, ListNode end) {
        end = end.next;

        ListNode pre = null;
        ListNode cur = start;
        ListNode next = null;

        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }


}
