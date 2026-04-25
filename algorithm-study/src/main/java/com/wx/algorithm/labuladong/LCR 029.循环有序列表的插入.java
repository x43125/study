/*
 * @lc app=leetcode.cn id=LCR 029 lang=java
 * @lcpr version=30403
 *
 * [LCR 029] 循环有序列表的插入
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    public Node insert(Node head, int insertVal) {
        Node insertNode = new Node();
        insertNode.val = insertVal;

        if (head == null) {
            insertNode.next = insertNode;
            return insertNode;
        }

        if (head == head.next) {
            head.next = insertNode;
            insertNode.next = head;
            return head;
        }

        Node node = head;
        while (node.next != head) {
            if (node.val <= insertVal && node.next.val >= insertVal) {
                insertNode.next = node.next;
                node.next = insertNode;
                break;
            } else if (node.next.val < node.val
                    && ((insertVal < node.val && insertVal < node.next.val)
                            || (insertVal > node.val && insertVal > node.next.val))) {
                insertNode.next = node.next;
                node.next = insertNode;
                break;
            }

            node = node.next;
        }

        if (node.next == head) {
            insertNode.next = node.next;
            node.next = insertNode;
        }

        return head;
    }
}
// @lc code=end

/*
 * // @lcpr case=start
 * // [3,4,1]\n2\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // []\n1\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [1]\n0\n
 * // @lcpr case=end
 * 
 */
