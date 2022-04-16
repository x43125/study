import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Paint {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 输入：root = [4,2,7,1,null,5,null,null,null,null,6]
     * ops = [[0,2,2],[1,1,5],[0,4,5],[1,5,7]]
     * 
     * 输出：5
     * 
     * 解释：
     * 第 0 次操作，将值为 2 的节点染蓝；
     * 第 1 次操作，将值为 1、2、4、5 的节点染红；
     * 第 2 次操作，将值为 4、5 的节点染蓝；
     * 第 3 次操作，将值为 5、6、7 的节点染红；
     * 因此，最终值为 1、2、5、6、7 的节点为红色节点，返回数量 5
     * 
     * @param root
     * @param ops
     * @return
     */
    public static int getNumber(TreeNode root, int[][] ops) {
        int[] color = new int[10 * 10 * 10 * 10 * 10];

        List<int[]> list = new LinkedList<>();
        for (int i = 0; i < ops.length; i++) {
            int[] tempArr = ops[i];
            int colorFlag = tempArr[0];
            int left = tempArr[1];
            int right = tempArr[2];

            if (colorFlag == 0) {
                // 染红
                for (int j = 0; j < list.size(); j++) {
                    int[] temp = list.get(j);
                    int[] next = list.get(j + 1);
                    if (left > temp[1] && right < next[0]) {
                        list.add(j + 1, new int[] { left, right });
                        break;
                    } else if (left <= temp[1] && left >= temp[0]) {
                        temp[1] = right;
                        list.set(j, temp);
                        break;
                    } else if (left < temp[0]) {
                        list.set(j, new int[] { left, right });
                        break;
                    } else if (right >= next[0] && right <= next[1]) {
                        next[0] = left;
                        list.set(j + 1, next);
                        break;
                    } else if (right > next[1]) {
                        list.set(j + 1, new int[] { left, right });
                        break;
                    } else if (left <= temp[1] && left >= temp[0] && right >= next[0] && right <= next[1]) {
                        list.set(j, new int[] { temp[0], next[1] });
                        list.remove(j + 1);
                        break;
                    } else if (left <= temp[1] && right >= next[0] && right <= next[1]) {
                        list.set(j, new int[] { left, next[1] });
                        break;
                    } else if (left <= temp[1] && left >= temp[0] && right >= next[0]) {
                        list.set(j, new int[] { temp[0], right });
                        break;
                    }
                }
            } else {
                // 染蓝
                for (int j = 0; j < list.size(); j++) {
                    int[] temp = list.get(j);
                    int[] next = list.get(j + 1);
                    if (left > temp[1] && right < next[0]) {
                        break;
                    } else if (left <= temp[1] && left >= temp[0]) {
                        temp[1] = left;
                        list.set(j, temp);
                        break;
                    } else if (left < temp[0]) {
                        list.remove(j);
                        break;
                    } else if (right >= next[0] && right <= next[1]) {
                        next[0] = right;
                        list.set(j + 1, next);
                        break;
                    } else if (right > next[1]) {
                        list.remove(j + 1);
                        break;
                    } else if (left <= temp[1] && left >= temp[0] && right >= next[0] && right <= next[1]) {
                        temp[1] = left;
                        list.set(j, temp);
                        next[0] = right;
                        list.set(j + 1, next);
                        break;
                    } else if (left <= temp[1] && right >= next[0] && right <= next[1]) {
                        next[0] = right;
                        list.set(j + 1, next);
                        temp[1] = left;
                        list.set(j, temp);
                        break;
                    } else if (left <= temp[1] && left >= temp[0] && right >= next[0]) {
                        next[0] = right;
                        list.set(j + 1, next);
                        temp[1] = left;
                        list.set(j, temp);
                        break;
                    }
                }
            }
        }

        int sum = 0;
        for (int[] is : list) {
            sum += (is[1] - is[0]);
        }

        return sum;
    }

    

}
