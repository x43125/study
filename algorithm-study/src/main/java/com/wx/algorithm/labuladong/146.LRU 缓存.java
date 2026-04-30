/*
 * @lc app=leetcode.cn id=146 lang=java
 * @lcpr version=30403
 *
 * [146] LRU 缓存
 */

// @lc code=start

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class LRUCache {

    int capacity;
    Map<Integer, Integer> map;
    Deque<Integer> qDeque;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.qDeque = new LinkedList<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            move2head(key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            move2head(key);
        } else {
            if (map.size() == capacity) {
                removeLast();
            }
            qDeque.offerFirst(key);
        }
        map.put(key, value);
    }

    private void removeLast() {
        int key = qDeque.pollLast();
        map.remove(key);
    }

    private void move2head(int key) {
        qDeque.remove(key);
        qDeque.offerFirst(key);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

/*
 * // @lcpr case=start
 * //
 * ["LRUCache","put","put","get","put","get","put","get","get","get"]\n[[2],[1,1
 * ],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]\n
 * // @lcpr case=end
 * 
 */
