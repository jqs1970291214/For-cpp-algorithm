/**
 * @Descriptions
 * @Company
 * @Author Junqson
 * @Date 2018/12/7 20:46
 * @Version 1.0
 */
public class LinkedListTest {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6, 1, 2};
        LinkedNode head = LinkedNode.constructFromArrayWithoutHead(arr);
        LinkedNode.travel(head);
        System.out.println(LinkedNode.centerNode(head).data);
        LinkedNode.travel(LinkedNode.reverse(head));
        int[] arra = {1, 1, 1};
        int[] arrb = {2, 4, 6};
        LinkedNode lista = LinkedNode.constructFromArrayWithoutHead(arra);
        LinkedNode listb = LinkedNode.constructFromArrayWithoutHead(arrb);
        LinkedNode merged = LinkedNode.merge(lista, listb);
        LinkedNode.travel(merged);
        System.out.println(LinkedNode.centerNode(merged).data);
        LinkedNode.travel(LinkedNode.removeTheLastK(merged, 5));
        int[] circuit = {1, 2, 3, 4, 5, 6, 7};
        LinkedNode cirhead = LinkedNode.constructFromArrayWithoutHead(circuit);
        LinkedNode tail = cirhead;
        while (tail.next != null) {
            tail = tail.next;
        }
        System.out.println(LinkedNode.circuitEntry(cirhead) == null);
        tail.next = cirhead;
        System.out.println(LinkedNode.circuitEntry(cirhead).data);
        tail.next = cirhead.next;
        System.out.println(LinkedNode.circuitEntry(cirhead).data);
    }
}
class LinkedNode {
    public int data;
    public LinkedNode next;

    /**
     * 从数组构造带头结点单链表
     * @param arr
     * @return
     */
    public static LinkedNode constructFromArray(int[] arr) {
        LinkedNode head = new LinkedNode();
        LinkedNode p = head;
        if (arr.length > 0) {
            for (int i = 0; i < arr.length; i++) {
                p.next = new LinkedNode();
                p.next.data = arr[i];
                p = p.next;
            }
        }
        return head;
    }

    /**
     * 从数组构造不带头结点的单链表
     */
    public static LinkedNode constructFromArrayWithoutHead(int[] arr) {
        LinkedNode node = constructFromArray(arr);
        if (node.next == null) {
            return null;
        } else {
            return node.next;
        }
    }

    /**
     * 顺序输出
     */
    public static void travel(LinkedNode node) {
        if (node == null) {
            return;
        } else {
            while (node != null) {
                System.out.print(String.valueOf(node.data) + " ");
                node = node.next;
            }
            System.out.println();
        }
    }

    /**
     * 链表翻转
     */
    public static LinkedNode reverse(LinkedNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        LinkedNode front, current, next;
        front = null;
        current = node;
        next = node.next;
        while (next != null) {
            current.next = front;
            front = current;
            current = next;
            next = next.next;
        }
        current.next = front;
        return current;
    }

    /**
     * 有序链表合并
     */
    public static LinkedNode merge(LinkedNode lista, LinkedNode listb) {
        if (lista == null) {
            return listb;
        }
        if (listb == null) {
            return lista;
        }

        LinkedNode head,p;
        if (lista.data <= listb.data) {
            p = lista;
            lista = lista.next;
        } else {
            p = listb;
            listb = listb.next;
        }
        head = p;
        while (lista != null) {
            if (listb == null) {
                p.next = lista;
                break;
            }
            if (lista.data <= listb.data) {
                p.next = lista;
                p = p.next;
                lista = lista.next;
            } else {
                p.next = listb;
                p = p.next;
                listb = listb.next;
            }
        }

        if (listb != null) {
            p.next = listb;
        }

        return head;

    }


    /**
     * 删除倒数第k个
     */
    public static LinkedNode removeTheLastK(LinkedNode node, int k) {
        if (node == null || k <= 0) {
            return node;
        }
        int n = 0;
        LinkedNode p = node;
        while (p != null) {
            n++;
            p = p.next;
        }
        int loc = n - k + 1;
        if (loc > 0) {
            n = 0;
            p = node;
            while (p != null) {
                n++;
                if (n == loc - 1) {
                    p.next = p.next.next;
                    break;
                }
                p = p.next;
            }

        }
        return node;
    }

    /**
     * 求链表中间结点, 如果是偶数，返回第n / 2 + 1个
     */
    public static LinkedNode centerNode(LinkedNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        if (node.next.next == null) {
            return node.next;
        }
        LinkedNode slow, fast;
        slow = fast = node;
        while (fast != null) {
            if (fast.next == null || fast.next.next == null) {
                if (fast.next != null) {
                    slow = slow.next;
                }
                break;
            }
            slow = slow.next;
            fast = fast.next.next;

        }
        return slow;
    }


    /**
     * 判断是否有环路，返回环路起点
     */

    public static LinkedNode circuitEntry(LinkedNode node) {
        if (node == null || node.next == null) {
            //  无环路
            return null;
        }
        LinkedNode slow, fast;
        slow = fast = node;
        while (fast != null) {
            slow = slow.next;
            // 快指针到达边界，不存在环路
            if (fast.next == null || fast.next.next == null) {
                break;
            }
            fast = fast.next.next;
            // 此时找到环路
            if (slow == fast) {
                // 寻找环路起点
                slow = node;
                // 快慢指针同步
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }

        }
        return null;
    }


}
